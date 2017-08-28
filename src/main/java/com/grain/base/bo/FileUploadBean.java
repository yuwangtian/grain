package com.grain.base.bo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadBean {

    private CommonsMultipartFile fileData;

    private String fileName;

    private String targetPath;


    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public CommonsMultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile fileData) {
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
