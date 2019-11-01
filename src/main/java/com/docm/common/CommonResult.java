package com.docm.common;


import java.io.Serializable;

public class CommonResult implements Serializable {

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

	public CommonResult() {
        this.code = "0000";
        this.msg = "";
    }

    public CommonResult(Object data) {
        this();
        this.data = data;
    }

    public CommonResult(String msg) {
        this.code = "9999";
        this.msg = msg;
    }

    public CommonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public CommonResult(String code, String msg ,Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.code + ";" + this.msg;
	}

    
}
