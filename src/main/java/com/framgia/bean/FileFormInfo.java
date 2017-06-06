package com.framgia.bean;

import org.springframework.web.multipart.MultipartFile;

/**
 * FileForm.java description table File Import Data
 * 
 * @version 21/04/2017
 * @author phan.van.hieu@framgia.com
 */
public class FileFormInfo {
	private MultipartFile fileImport;
	private String nameTable;
	private String fileName;
	private String title;
	private String description;
	private Integer id;
	
	
	public FileFormInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNameTable() {
		return nameTable;
	}

	public void setNameTable(String nameTable) {
		this.nameTable = nameTable;
	}

	public MultipartFile getFileImport() {
		return fileImport;
	}

	public void setFileImport(MultipartFile fileImport) {
		this.fileImport = fileImport;
	}

}
