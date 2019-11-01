package com.docm.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.docm.common.CommonResult;
import com.docm.utils.HttpServletUtil;

@Component
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler {
 
	private Logger LOGGER = Logger.getLogger(MyAuthenticationFailHandler.class);
    public static final String RETURN_TYPE = "json"; // 登录失败时，用来判断是返回json数据还是跳转html页面
 
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
 
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    	LOGGER.info("登录失败:" + exception.getMessage());
    	LOGGER.info("username=>" + request.getParameter("username"));
 
        if (RETURN_TYPE.equals("html")) {
            redirectStrategy.sendRedirect(request, response, "/login/index?error=true");
        } else {
        	HttpServletUtil.responseWrite(request, response, new CommonResult("301", "登录失败"));
        }
    }
}
