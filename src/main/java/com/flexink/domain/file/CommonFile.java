package com.flexink.domain.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.flexink.common.code.FxBootType.Deleted;
import com.flexink.common.domain.BaseJpaModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "FILE_L")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommonFile extends BaseJpaModel<Long>{
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TARGET_TYPE", length = 50)
    //Comment(value = "타겟 TYPE")
    private String targetType;

    @Column(name = "TARGET_ID", length = 100)
    //Comment(value = "타겟 ID")
    private String targetId;

    @Column(name = "FILE_NM", columnDefinition = "TEXT")
    //Comment(value = "실제 파일명")
    private String fileNm;

    @Column(name = "SAVE_NM", columnDefinition = "TEXT")
    //Comment(value = "저장 파일명")
    private String saveNm;

    @Column(name = "FILE_TYPE", length = 30)
    //Comment(value = "파일 타입")
    private String fileType;

    @Column(name = "EXTENSION", length = 10)
    //Comment(value = "확장자")
    private String extension;

    @Column(name = "FILE_SIZE")
    //Comment(value = "파일 크기")
    private Long fileSize;

    @Column(name = "DEL_YN", length = 1)
    @Enumerated(EnumType.STRING)
    //Comment(value = "삭제여부")
    private Deleted delYn = Deleted.N;

    @Column(name = "FILE_DESC", columnDefinition = "TEXT")
    //Comment(value = "설명")
    private String desc;

    @Column(name = "SORT")
    //Comment(value = "정렬")
    private Integer sort;

    @JsonIgnore
    @Transient
    private String _preview;

    @JsonIgnore
    @Transient
    private String _thumbnail;

    @JsonIgnore
    @Transient
    private String _download;

    @JsonProperty("preview")
    public String preview() {
        if (StringUtils.isEmpty(_preview)) {
            return "/sample/files/preview?id=" + getId();
        }
        return _preview;
    }

    @JsonProperty("thumbnail")
    public String thumbnail() {
        if (StringUtils.isEmpty(_thumbnail)) {
            return "/sample/files/thumbnail?id=" + getId();
        }
        return _thumbnail;
    }

    @JsonProperty("download")
    public String download() {
        if (StringUtils.isEmpty(_download)) {
            return "/sample/files/download?id=" + getId();
        }
        return _download;
    }

    @Transient
    public String getThumbnailFileName() {
        return FilenameUtils.getBaseName(getSaveNm()) + "-thumbnail" + "." + FilenameUtils.getExtension(getSaveNm());
    }
    
}
