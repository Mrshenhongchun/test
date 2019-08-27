package com.cy.pj.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.util.IPUtils;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Service
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SysLogAspect {
	/**
	 * @Pointcut注解用于定义切入点 bean表达式为切入点表达式, bean表达式内部指定的bean对象中 所有方法为切入点(进行功能扩展的点)
	 */

	@Pointcut("bean(*ServiceImpl)")
	public void logPointCut() {
	}

	/**
	 * @Around 注解描述的方法为一个环绕通知, 此通知可以目标方法执行之前和之后添加 扩展功能,甚至控制目标方法执行.
	 * @param jp 连接点对象, ProceedingJoinPoint类型的连接点只能应用在环绕通知,
	 * @return 目标方法的执行结果
	 * @throws Throwable 环绕通知方法最好抛出Throwable类型异常.
	 */
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		try {
			Long t1 = System.currentTimeMillis();
			log.info("start:" + t1);
			Object result = jp.proceed();// 调用下一个切面方法或目标方法
			Long t2 = System.currentTimeMillis();
			log.info("after:" + t2);
			saveObject(jp, (t2 - t1));
			return result;
		} catch (Throwable e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private SysLogDao sysLogDao;

	private void saveObject(ProceedingJoinPoint jp, long time) throws Exception {
		// 1.获取用户行为日志
		MethodSignature ms = (MethodSignature) jp.getSignature();
		// 1.1获取方法所在的类及方法名
		Class<?> targetCls = jp.getTarget().getClass();
		// 1)获取方法签名(封装了方法的相关信息)
		Method targetmethod = ms.getMethod();
		String clsMethod=
		targetCls.getName()+"."+targetmethod.getName();
		// 2)获取目标对象的字节码对象(通过它可以获取类全名)
		Object[] args = jp.getArgs();
		ObjectMapper om = new ObjectMapper();
		String params = om.writeValueAsString(args);	
		// 3)获取目标类中的方法(通过其对象获取方法名)
		RequiredLog rlog = targetmethod.getAnnotation(RequiredLog.class);
		String operation="operation";
    	if(rlog!=null) {operation=rlog.value();}
    	//2.封装用户行为日志信息
    	SysLog log=new SysLog();
    	log.setIp(IPUtils.getIpAddr());
    	log.setMethod(clsMethod);//类全名+方法名
    	log.setParams(params);//执行方法时传递的实际参数
    	log.setOperation(operation);
    	log.setUsername("admin");//登陆用户
    	log.setTime(time);
    	log.setCreatedTime(new Date());
		// 3.将用户行为日志持久化到数据库
    	sysLogService.saveObjiet(log);

	}

}
