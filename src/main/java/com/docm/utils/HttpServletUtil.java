package com.docm.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.docm.common.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.bitwalker.useragentutils.UserAgent;

public class HttpServletUtil {
	
	private static Logger LOGGER = Logger.getLogger(HttpServletUtil.class);
	
	public static String getbaseUrl(HttpServletRequest request, HttpServletResponse response) {
		
		String requestUrl = request.getRequestURL().toString();
		String url = requestUrl.substring(0,requestUrl.indexOf(request.getServerName())) // 兼容https请求
				        + request.getServerName() //服务器地址  
				        + ":"   
				        + request.getServerPort()           //端口号  
				        + request.getContextPath();      //项目名称  
		return url;
	}
	
	public static boolean isPhone(HttpServletRequest request, HttpServletResponse response) {
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		LOGGER.info(userAgent.getOperatingSystem());
		String system = userAgent.getOperatingSystem().toString();
		if(system.toUpperCase().indexOf("MOBILE") != -1 || system.toUpperCase().indexOf("IPHONE") != -1 || system.toUpperCase().indexOf("ANDROID") != -1 ) {
			return true;
		}
		return false;
	}
	
	
	public static void responseWrite(HttpServletRequest request, HttpServletResponse response , CommonResult commonResult) {
		response.setContentType("application/json;charset=UTF-8");
    	PrintWriter writer = null;
    	try {
            writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(commonResult));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
	}
	

}
