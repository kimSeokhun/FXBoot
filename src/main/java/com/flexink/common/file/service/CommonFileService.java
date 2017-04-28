package com.flexink.common.file.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.flexink.common.code.FxBootType;
import com.flexink.common.code.Types;
import com.flexink.common.domain.BaseService;
import com.flexink.common.utils.ArrayUtils;
import com.flexink.common.utils.EncodeUtils;
import com.flexink.domain.file.CommonFile;
import com.flexink.domain.file.CommonFileRepository;
import com.flexink.domain.file.QCommonFile;
import com.flexink.vo.ParamsVo;
import com.flexink.vo.file.UploadParameters;
import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;


@Service
@Slf4j
public class CommonFileService extends BaseService<CommonFile, Long> implements InitializingBean {
    private CommonFileRepository commonFileRepository;
    
    QCommonFile qCommonFile = QCommonFile.commonFile;

    @Value("${axboot.upload.repository}")
    public String uploadRepository;

    @Autowired
    public CommonFileService(CommonFileRepository commonFileRepository) {
    	super(CommonFile.class, commonFileRepository);
        this.commonFileRepository = commonFileRepository;
    }

    public void createBaseDirectory() {
        try {
            FileUtils.forceMkdir(new File(uploadRepository));
        } catch (IOException e) {
        }
    }

    public String getTempDir() {
        return System.getProperty("java.io.tmpdir");
    }

    public File multiPartFileToFile(MultipartFile multipartFile) throws IOException {
        String baseDir = getTempDir() + "/" + UUID.randomUUID().toString();

        FileUtils.forceMkdir(new File(baseDir));

        String tmpFileName = baseDir + "/" + FilenameUtils.getName(multipartFile.getOriginalFilename());

        File file = new File(tmpFileName);

        multipartFile.transferTo(file);
        return file;
    }

    @Transactional
    public CommonFile upload(MultipartFile multipartFile, String targetType, String targetId, int sort) throws IOException {
        return upload(multiPartFileToFile(multipartFile), targetType, targetId, sort);
    }

    @Transactional
    public CommonFile upload(File file, String targetType, String targetId, int sort) throws IOException {
        UploadParameters uploadParameters = new UploadParameters();
        uploadParameters.setFile(file);
        uploadParameters.setTargetType(targetType);
        uploadParameters.setTargetId(targetId);
        uploadParameters.setSort(sort);

        return upload(uploadParameters);
    }

    @Transactional
    public CommonFile upload(UploadParameters uploadParameters) throws IOException {
    	log.debug("UploadParameters");
        File uploadFile = uploadParameters.getFile();

        if (uploadFile == null && uploadParameters.getMultipartFile() != null) {
            uploadFile = multiPartFileToFile(uploadParameters.getMultipartFile());
        }

        String targetType = uploadParameters.getTargetType();
        String targetId = uploadParameters.getTargetId();
        String desc = uploadParameters.getDesc();

        boolean deleteIfExist = uploadParameters.isDeleteIfExist();
        boolean thumbnail = uploadParameters.isThumbnail();

        int sort = uploadParameters.getSort();
        int thumbnailWidth = uploadParameters.getThumbnailWidth();
        int thumbnailHeight = uploadParameters.getThumbnailHeight();

        String fileName = FilenameUtils.getName(uploadFile.getName());
        String extension = FilenameUtils.getExtension(fileName);
        String fileType = getFileType(extension);

        String baseName = UUID.randomUUID().toString();
        String saveName = baseName + "." + extension;
        String savePath = getSavePath(saveName);

        File file = new File(savePath);
        FileUtils.copyFile(uploadFile, file);

        if (deleteIfExist) {
            deleteByTargetTypeAndTargetId(targetType, targetId);
        }

        CommonFile commonFile = new CommonFile();
        commonFile.setTargetType(targetType);
        commonFile.setTargetId(targetId);
        commonFile.setFileNm(fileName);
        commonFile.setSaveNm(saveName);
        commonFile.setSort(sort);
        commonFile.setDesc(desc);
        commonFile.setFileType(fileType);
        commonFile.setExtension(FilenameUtils.getExtension(fileName).toUpperCase());
        commonFile.setFileSize(file.length());

        if (fileType.equals(Types.FileType.IMAGE) && thumbnail) {
            try {
                Thumbnails.of(file)
                        //.crop(Positions.CENTER)
                        .size(thumbnailWidth, thumbnailHeight)
                        .toFiles(new File(getBasePath()), Rename.SUFFIX_HYPHEN_THUMBNAIL);
            } catch (Exception e) {
            }
        }

        FileUtils.deleteQuietly(uploadFile);

        repository.save(commonFile);

        return commonFile;
    }

    private String getFileType(String extension) {
        switch (extension.toUpperCase()) {
            case Types.FileExtensions.PNG:
            case Types.FileExtensions.JPG:
            case Types.FileExtensions.JPEG:
            case Types.FileExtensions.GIF:
            case Types.FileExtensions.BMP:
            case Types.FileExtensions.TIFF:
            case Types.FileExtensions.TIF:
                return Types.FileType.IMAGE;

            case Types.FileExtensions.PDF:
                return Types.FileType.PDF;

            default:
                return Types.FileType.ETC;
        }
    }

    public ResponseEntity<byte[]> downloadById(Long id) throws IOException {
        CommonFile commonFile = commonFileRepository.findOne(id);
        return download(commonFile);
    }

    public ResponseEntity<byte[]> downloadByTargetTypeAndTargetId(String targetType, String targetId) throws IOException {
        //CommonFile commonFile = commonFileRepository.findByTargetTypeAndTargetId(targetType, targetId);
        CommonFile commonFile = from(qCommonFile).where(qCommonFile.targetType.eq(targetType).and(qCommonFile.targetId.eq(targetId))).fetchOne();
        return download(commonFile);
    }

    public ResponseEntity<byte[]> download(CommonFile commonFile) throws IOException {
        if (commonFile == null)
            return null;

        byte[] bytes = FileUtils.readFileToByteArray(new File(getSavePath(commonFile.getSaveNm())));

        String fileName = EncodeUtils.encodeDownloadFileName(commonFile.getFileNm());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    public void preview(HttpServletResponse response, Long id, String type) throws IOException {
        CommonFile commonFile = commonFileRepository.findOne(id);

        if (commonFile == null)
            return;

        MediaType mediaType = null;
        String imagePath = "";

        switch (commonFile.getExtension()) {
            case Types.FileExtensions.JPEG:
            case Types.FileExtensions.JPG:
                mediaType = MediaType.IMAGE_JPEG;
                break;

            case Types.FileExtensions.PNG:
                mediaType = MediaType.IMAGE_PNG;
                break;

            case Types.FileExtensions.GIF:
                mediaType = MediaType.IMAGE_GIF;
                break;

            default:
        }

        switch (type) {
            case Types.ImagePreviewType.ORIGIN:
                imagePath = getSavePath(commonFile.getSaveNm());
                break;

            case Types.ImagePreviewType.THUMBNAIL:
                imagePath = getSavePath(commonFile.getThumbnailFileName());
                break;
        }

        if (mediaType != null) {
            byte[] bytes = FileUtils.readFileToByteArray(new File(imagePath));

            response.setContentType(mediaType.toString());
            response.setContentLength(bytes.length);
            IOUtils.copy(FileUtils.openInputStream(new File(imagePath)), response.getOutputStream());
        }
    }

    public void preview(HttpServletResponse response, Long id) throws IOException {
        preview(response, id, Types.ImagePreviewType.ORIGIN);
    }

    public void thumbnail(HttpServletResponse response, Long id) throws IOException {
        preview(response, id, Types.ImagePreviewType.THUMBNAIL);
    }

    public String getBasePath() {
        return uploadRepository;

    }

    public String getSavePath(String saveName) {
        return getBasePath() + "/" + saveName;
    }

    public byte[] getFileBytes(String saveName) throws IOException {
        return FileUtils.readFileToByteArray(new File(getSavePath(saveName)));
    }

    public Page<CommonFile> getList(ParamsVo paramsVo) {
        String targetType = paramsVo.getString("targetType", "");
        String targetId = paramsVo.getString("targetId", "");
        String delYn = paramsVo.getString("delYn", "");
        String targetIds = paramsVo.getString("targetIds", "");
        paramsVo.addSort("sort", Sort.Direction.ASC);
        paramsVo.addSort("id", Sort.Direction.DESC);

        Pageable pageable = paramsVo.getPageable();

        BooleanBuilder builder = new BooleanBuilder();
        
        if (StringUtils.isNotEmpty(targetType)) {
        	builder.and(qCommonFile.targetType.eq(targetType));
        }

        if (StringUtils.isNotEmpty(targetId)) {
        	builder.and(qCommonFile.targetId.eq(targetId));
        }

        if (StringUtils.isNotEmpty(delYn)) {
        	FxBootType.Deleted deleted = FxBootType.Deleted.valueOf(delYn);
        	builder.and(qCommonFile.delYn.eq(deleted));
        }

        if (StringUtils.isNotEmpty(targetIds)) {
        	// Set<String> _ids = Arrays.stream(targetIds.split(",")).collect(Collectors.toSet());		// java8
        	// java7
        	String ids[] = targetIds.split(",");
        	Set<String> _ids = new HashSet<String>(Arrays.asList(ids));
        	builder.and(qCommonFile.targetId.in(_ids));
        }

        return commonFileRepository.findAll(builder, pageable);
    }

    public CommonFile get(ParamsVo paramsVo) {
        List<CommonFile> commonFiles = getList(paramsVo).getContent();
        return ArrayUtils.isEmpty(commonFiles) ? null : commonFiles.get(0);
    }

    public CommonFile get(String targetType, String targetId) {
    	ParamsVo requestParams = new ParamsVo();
        requestParams.put("targetType", targetType);
        requestParams.put("targetId", targetId);

        return get(requestParams);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        createBaseDirectory();
    }
    
    @Transactional
    public void deleteFiles(List<CommonFile> files) {
    	for(CommonFile file : files) {
    		deleteFile(file.getId());
    	}
    }

    @Transactional
    public void deleteFile(Long id) {
    	commonFileRepository.delete(id);
    }

    @Transactional
    public void deleteByTargetTypeAndTargetIds(String targetType, Set<String> targetIds) {
    	//Iterable<CommonFile> list = commonFileRepository.findAll(qCommonFile.targetType.eq(targetType).and(qCommonFile.targetId.in(targetIds)));
    	//commonFileRepository.delete(list);
    	delete(qCommonFile).where(qCommonFile.targetType.eq(targetType).and(qCommonFile.targetId.in(targetIds))).execute();
    }

    @Transactional
    private void deleteByTargetTypeAndTargetId(String targetType, String targetId) {
        //CommonFile file = commonFileRepository.findOne(qCommonFile.targetType.eq(targetType).and(qCommonFile.targetId.eq(targetId)));
        //commonFileRepository.delete(file);
        delete(qCommonFile).where(qCommonFile.targetType.eq(targetType).and(qCommonFile.targetId.eq(targetId))).execute();
    }

    @Transactional
    public void updateOrDelete(List<CommonFile> commonFileList) {
        for (CommonFile file : commonFileList) {
            if (file.isDeleted()) {
                deleteFile(file.getId());
            } else {
                //commonFileRepository.updateCommonFile(file);
        		update(qCommonFile).set(qCommonFile.targetType, file.getTargetType()).set(qCommonFile.targetId, file.getTargetId()).where(qCommonFile.id.eq(file.getId())).execute();
            }
        }
    }
}
