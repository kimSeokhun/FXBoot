package com.flexink.common.file.domain;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class UploadParameters {

	private File file;

	private MultipartFile multipartFile;

	private String targetType;

	private String targetId;

	private int sort;

	private String desc;

	private boolean deleteIfExist;

	private boolean thumbnail = true;

	private int thumbnailWidth = 150;

	private int thumbnailHeight = 150;
}
