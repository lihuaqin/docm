package com.docm.config;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docm.common.CommonResult;
import com.docm.exception.MyException;

/**
 * 全局异常处理
 *
 * @author lhq
 * @date 2019/9/18
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);
	
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public CommonResult handleMyException(MyException myException) {
    	LOGGER.info(">>>>>>>>>>>>>>>>handleCustomException>>>>>>>>>>>>" + myException.getMessage());
        return new CommonResult(myException.getCode(),myException.getMessage());
    }
    
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception exception) {
    	LOGGER.info(">>>>>>>>>>>>>>>>handleCustomException>>>>>>>>>>>>" + exception.getMessage());
    	
    	if( exception instanceof MyException) {
			return new CommonResult("301",exception.getMessage());
		}
        return new CommonResult("301","系统错误，请重试!");
    }
}
