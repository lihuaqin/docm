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
@TableName("t_properties")
public class Propertie extends BaseVo {

    private static final long serialVersionUID = 1L;

    @TableField("propertieKey")
    private String propertieKey;

    @TableField("propertieValue")
    private String propertieValue;

    public String getPropertieKey() {
        return propertieKey;
    }

    public void setPropertieKey(String propertieKey) {
        this.propertieKey = propertieKey;
    }
    public String getPropertieValue() {
        return propertieValue;
    }

    public void setPropertieValue(String propertieValue) {
        this.propertieValue = propertieValue;
    }

    @Override
    public String toString() {
        return "Properties{" +
        "propertieKey=" + propertieKey +
        ", propertieValue=" + propertieValue +
        "}";
    }
}
