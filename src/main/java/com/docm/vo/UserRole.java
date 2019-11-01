package com.docm.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.docm.vo.BaseVo;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhq
 * @since 2019-11-01
 */
@TableName("t_user_role")
public class UserRole extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String roleId;

    /**
     * 0-正常 1-删除 2-禁用
     */
    private Integer status;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        "userId=" + userId +
        ", roleId=" + roleId +
        ", status=" + status +
        "}";
    }
}
