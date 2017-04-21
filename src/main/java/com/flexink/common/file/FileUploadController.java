package com.flexink.common.file;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.flexink.common.file.domain.CommonFile;
import com.flexink.common.file.domain.CommonFileService;
import com.flexink.common.file.domain.UploadParameters;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/sample/files")
public class FileUploadController {

    @Autowired
    private CommonFileService commonFileService;
    
    @GetMapping("/")
    public String filePage() {
    	return "/sample/file";
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public CommonFile upload(
            @RequestParam(value = "file") MultipartFile multipartFile,
            @RequestParam(value = "targetType", required = false) String targetType,
            @RequestParam(value = "targetId", required = false) String targetId,
            @RequestParam(value = "sort", required = false, defaultValue = "0") int sort,
            @RequestParam(value = "deleteIfExist", required = false, defaultValue = "false") boolean deleteIfExist,
            @RequestParam(value = "desc", required = false) String desc,
            @RequestParam(value = "thumbnail", required = false, defaultValue = "false") boolean thumbnail,
            @RequestParam(value = "thumbnailWidth", required = false, defaultValue = "150") int thumbnailWidth,
            @RequestParam(value = "thumbnailHeight", required = false, defaultValue = "150") int thumbnailHeight) throws IOException {
    	
    	
        UploadParameters uploadParameters = new UploadParameters();
        uploadParameters.setMultipartFile(multipartFile);
        uploadParameters.setTargetType(targetType);
        uploadParameters.setTargetId(targetId);
        uploadParameters.setSort(sort);
        uploadParameters.setDeleteIfExist(deleteIfExist);
        uploadParameters.setDesc(desc);
        uploadParameters.setThumbnail(thumbnail);
        uploadParameters.setThumbnailWidth(thumbnailWidth);
        uploadParameters.setThumbnailHeight(thumbnailHeight);

        return commonFileService.upload(uploadParameters);
    }

    /*@RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Responses.PageResponse list(RequestParams<CommonFile> requestParams) {
        Page<CommonFile> files = commonFileService.getList(requestParams);
        return Responses.PageResponse.of(files);
    }*/

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public List<CommonFile> updateOrDelete(@RequestBody List<CommonFile> file) {
        commonFileService.updateOrDelete(file);
        return file;
    }

    @RequestMapping(value = "/preview", method = RequestMethod.GET)
    public void preview(HttpServletResponse response, @RequestParam Long id) throws IOException {
        commonFileService.preview(response, id);
    }

    @RequestMapping(value = "/thumbnail", method = RequestMethod.GET)
    public void thumbnail(HttpServletResponse response, @RequestParam Long id) throws IOException {
        commonFileService.thumbnail(response, id);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam Long id) throws IOException {
        return commonFileService.downloadById(id);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET, params = {"targetType", "targetId"})
    public ResponseEntity<byte[]> downloadByTargetTypeAndTargetId(@RequestParam String targetType, @RequestParam String targetId) throws IOException {
        return commonFileService.downloadByTargetTypeAndTargetId(targetType, targetId);
    }
}
