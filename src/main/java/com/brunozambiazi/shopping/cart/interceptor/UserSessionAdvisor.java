package com.brunozambiazi.shopping.cart.interceptor;

import java.lang.reflect.Method;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserSessionAdvisor extends AbstractPointcutAdvisor {

	private static final long serialVersionUID = -6818299521049083683L;

	private final StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {

		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return targetClass.isAnnotationPresent(Service.class);
		}

	};

	@Autowired
	private UserSessionInterceptor uerSessionInterceptor;

	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}

	@Override
	public Advice getAdvice() {
		return uerSessionInterceptor;
	}

}
