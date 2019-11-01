package com.docm.config.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.docm.common.CommonResult;
import com.docm.utils.HttpServletUtil;
import com.docm.vo.LoginUser;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
	private Logger LOGGER = Logger.getLogger(MyAuthenticationSuccessHandler.class);
    public static final String RETURN_TYPE = "json"; // 登录成功时，用来判断是返回json数据还是跳转html页面
    
    @Value("${zdketang.url.qian}")
	private  String ZDKETANG_URL_QIAN ;
    
    @Value("${zdketang.meet.flag}")
	private  String ZDKETANG_MEET_FLAG ;
 
 
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public synchronized void   onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	LOGGER.info("登录成功");
    	LOGGER.info("username=>" + request.getParameter("username"));
        if(RETURN_TYPE.equals("html")) {
        	
        } else {
        	HttpServletUtil.responseWrite(request, response, setMeetingData(request, response ,ZDKETANG_URL_QIAN ,ZDKETANG_MEET_FLAG));
        }
    }
    
    public static CommonResult setMeetingData(HttpServletRequest request, HttpServletResponse response ,String ZDKETANG_URL_QIAN,String ZDKETANG_MEET_FLAG ) throws IOException, ServletException {
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		Cookie cookie = new Cookie(uuid, uuid);
		response.addCookie(cookie);
		
    	String interactionId = SecurityContextHolder.getContext().getAuthentication().getName();
    	Map<String ,Object> data = new HashMap<String ,Object>();
    	data.put("uuid", uuid);
    	data.put("interactionId", interactionId);
    	data.put("ZDKETANG_URL_QIAN", ZDKETANG_URL_QIAN);
    	data.put("ZDKETANG_MEET_FLAG", ZDKETANG_MEET_FLAG);
    	
    	request.getSession().setAttribute("uuid", uuid);
    	request.getSession().setAttribute("interactionId", interactionId);
    	request.getSession().setAttribute("ZDKETANG_URL_QIAN", ZDKETANG_URL_QIAN);
    	request.getSession().setAttribute("ZDKETANG_MEET_FLAG", ZDKETANG_MEET_FLAG);
    	request.getSession().setAttribute("username", ((LoginUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNickName());
    	request.getSession().setAttribute("isadmin", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().indexOf("ROLE_ADMIN") != -1);
    	//是否有admin角色
    	data.put("isadmin", SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString().indexOf("ROLE_ADMIN") != -1);
    	
    	
		return new CommonResult("200", "success" , data);
    }
}
