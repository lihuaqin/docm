package com.docm.aop;

import java.lang.reflect.Field;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Aspect // 切面注解
//@Component // 构成
public class BaseMapperAopTest {
	// 设置在ManagerController的login方法的切点
	@Pointcut("execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.update*(..))")
	public void update() {// 切点映射，命名不规定
		System.out.println("aspect");
	}

	// 在本类的login执行之前
	@Before("login()")
	public void beforelogin(JoinPoint point) throws Exception {
		System.out.println("before");
		Object[] args = point.getArgs();
		Object arg = args[0];
		Field[] fields = arg.getClass().getDeclaredFields();
		for (Field f : fields) { 
			f.setAccessible(true);
			
			System.out.println(f.get(arg));
			f.set(arg, "ttttt");
			
			
		}
	}

	// 在本类的login执行之后执行
	@After("login()")
	public void afterlogin() {
		System.out.println("after");
	}
}
