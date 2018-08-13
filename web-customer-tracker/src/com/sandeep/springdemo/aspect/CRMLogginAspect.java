package com.sandeep.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLogginAspect {

	private Logger myLogger=Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.sandeep.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
		
	}
	@Pointcut("execution(* com.sandeep.springdemo.service.*.*(..))")
	private void forServicePackage() {
		
	}
	@Pointcut("execution(* com.sandeep.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
		
	}
	
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		String theMethod=theJoinPoint.getSignature().toShortString();
		myLogger.info("@Before calling method: "+theMethod);
		
		Object[] args=theJoinPoint.getArgs();
		for(Object temp:args) {
			myLogger.info("--->argument : "+temp);
		}
	}
		
		@AfterReturning(
				pointcut="forAppFlow()",
				returning="theResult")
		public void afterReturning(JoinPoint theJoinPoint,Object theResult) {
			String theMethod=theJoinPoint.getSignature().toShortString();
			myLogger.info("@after returning method: "+theMethod);
			
			myLogger.info("==> result : "+theResult);
			
		}
		
	
}
