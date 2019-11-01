package com.docm.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.docm.vo.BaseVo;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhq
 * @since 2019-11-01
 */
@TableName("t_file")
public class Node extends BaseVo {

    private static final long serialVersionUID = 1L;

    @TableField("fileId")
    private String fileId;

    @TableField("fileName")
    private String fileName;

    @TableField("fileSize")
    private String fileSize;

    @TableField("fileParentFolder")
    private String fileParentFolder;

    @TableField("fileCreationDate")
    private String fileCreationDate;

    @TableField("fileCreator")
    private String fileCreator;

    @TableField("filePath")
    private String filePath;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    public String getFileParentFolder() {
        return fileParentFolder;
    }

    public void setFileParentFolder(String fileParentFolder) {
        this.fileParentFolder = fileParentFolder;
    }
    public String getFileCreationDate() {
        return fileCreationDate;
    }

    public void setFileCreationDate(String fileCreationDate) {
        this.fileCreationDate = fileCreationDate;
    }
    public String getFileCreator() {
        return fileCreator;
    }

    public void setFileCreator(String fileCreator) {
        this.fileCreator = fileCreator;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "File{" +
        "fileId=" + fileId +
        ", fileName=" + fileName +
        ", fileSize=" + fileSize +
        ", fileParentFolder=" + fileParentFolder +
        ", fileCreationDate=" + fileCreationDate +
        ", fileCreator=" + fileCreator +
        ", filePath=" + filePath +
        "}";
    }
}
