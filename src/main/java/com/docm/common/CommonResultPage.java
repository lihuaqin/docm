package com.docm.common;


import java.io.Serializable;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.docm.page.DataTablePageDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommonResultPage implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8573855566812280377L;

	/**
     * 1为返回正常， 其它code均为请求错误
     */
    private String code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 错误信息
     */
    private String msg;
    
    @JsonProperty("DT_RowId")
	private final String DT_RowId="1";
    
    

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getmsg() {
		return msg;
	}

	public void setmsg(String msg) {
		this.msg = msg;
	}

	public CommonResultPage() {
        this.code = "0000";
        this.msg = "";
    }

    public CommonResultPage(Object data) {
        this();
        this.data = data;
    }

    public CommonResultPage(String msg) {
        this.code = "9999";
        this.msg = msg;
    }

    public CommonResultPage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    @SuppressWarnings("unchecked")
	public CommonResultPage(String code, String msg ,Object data ,DataTablePageDto<Object> tablePage) {
    	long total = ((Page<Object>) data).getTotal();
    	tablePage.setRecordsTotal(total);
    	tablePage.setData(((Page<Object>) data).getRecords());
        this.code = code;
        this.msg = msg;
        this.data = tablePage;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.code + ";" + this.msg;
	}

	public String getDT_RowId() {
		return DT_RowId;
	}

	
    
}
