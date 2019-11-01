package com.docm.aop;

import java.lang.reflect.Method;
import java.util.Date;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.docm.vo.UserVo;

@Aspect // 切面注解
@Component // 构成
public class BaseMapperAop {
	
	private Logger LOGGER = Logger.getLogger(BaseMapperAop.class);

	@Autowired
	private HttpSession session;


	@Before("execution(* com.youwe.meet.dao.**.update*(..))")
	public void beforeUpdate(JoinPoint point) throws Exception {
		LOGGER.info("before-update");
		
//		Object[] args = point.getArgs();
//		Object arg = args[0];
//		Method[] methods = arg.getClass().getMethods();
//		for (Method m : methods) { 
//			if("setUpdateDate".equals(m.getName())){
//				m.setAccessible(true);
//				m.invoke(arg,new Date());
//			}
//			if("setUpdater".equals(m.getName())){
//				m.setAccessible(true);
//				m.invoke(arg,((UserVo)session.getAttribute("currentUser")).getId());
//			}
//			
//		}
		LOGGER.info("end-update");
	}
	
	
	@Before("execution(* com.youwe.meet.dao.**.insert(..))")
	public void beforeInsert(JoinPoint point) throws Exception {
		LOGGER.info("before-update");
		Object[] args = point.getArgs();
		Object arg = args[0];
		Method[] methods = arg.getClass().getMethods();
		for (Method m : methods) { 
			if("setUpdateDate".equals(m.getName()) || "setCreateDate".equals(m.getName())){
				m.setAccessible(true);
				m.invoke(arg,new Date());
			}
			if("setUpdater".equals(m.getName()) || "setCreater".equals(m.getName())){
				m.setAccessible(true);
				m.invoke(arg,((UserVo)session.getAttribute("currentUser")).getId());
			}
			
		}
		LOGGER.info("end-update");
	}
	

}
