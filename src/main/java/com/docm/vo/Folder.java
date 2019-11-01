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
@TableName("t_folder")
public class Folder extends BaseVo {

    private static final long serialVersionUID = 1L;

    @TableField("folderId")
    private String folderId;

    @TableField("folderName")
    private String folderName;

    @TableField("folderCreationDate")
    private String folderCreationDate;

    @TableField("folderCreator")
    private String folderCreator;

    @TableField("folderParent")
    private String folderParent;

    @TableField("folderConstraint")
    private Integer folderConstraint;

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }
    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
    public String getFolderCreationDate() {
        return folderCreationDate;
    }

    public void setFolderCreationDate(String folderCreationDate) {
        this.folderCreationDate = folderCreationDate;
    }
    public String getFolderCreator() {
        return folderCreator;
    }

    public void setFolderCreator(String folderCreator) {
        this.folderCreator = folderCreator;
    }
    public String getFolderParent() {
        return folderParent;
    }

    public void setFolderParent(String folderParent) {
        this.folderParent = folderParent;
    }
    public Integer getFolderConstraint() {
        return folderConstraint;
    }

    public void setFolderConstraint(Integer folderConstraint) {
        this.folderConstraint = folderConstraint;
    }

    @Override
    public String toString() {
        return "Folder{" +
        "folderId=" + folderId +
        ", folderName=" + folderName +
        ", folderCreationDate=" + folderCreationDate +
        ", folderCreator=" + folderCreator +
        ", folderParent=" + folderParent +
        ", folderConstraint=" + folderConstraint +
        "}";
    }
}
