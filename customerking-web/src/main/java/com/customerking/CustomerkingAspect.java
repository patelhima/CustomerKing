package com.customerking;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CustomerkingAspect {

	public static Logger LOGGER = null;

	@Pointcut("within(com.customerking..*.*.*)")
	protected void loggingOperation() {
	}

	@Before("loggingOperation()")
	@Order(1)
	public void logBefore(JoinPoint joinPoint) {
		LOGGER = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		LOGGER.debug("Start of execution of method " + joinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "loggingOperation()", returning = "result")
	@Order(2)
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		LOGGER = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		LOGGER.debug("End of execution of method " + joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = "within(com.customerking..*.*.*)", throwing = "e")
	@Order(3)
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		LOGGER = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		LOGGER.error("An exception has been thrown in " + joinPoint.getSignature().getName(), e);
	}

}
