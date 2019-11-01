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
@TableName("t_role")
public class Role extends BaseVo {

    private static final long serialVersionUID = 1L;

    private String role;

    /**
     * 0-正常 1-删除 2-禁用
     */
    private Integer status;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
        "role=" + role +
        ", status=" + status +
        "}";
    }
}
