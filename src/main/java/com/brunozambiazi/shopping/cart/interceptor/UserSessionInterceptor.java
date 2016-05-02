package com.brunozambiazi.shopping.cart.interceptor;

import com.brunozambiazi.shopping.cart.exception.InvalidUserSessionException;
import java.lang.annotation.Annotation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserSessionInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Annotation[][] argumentsAnnotations = invocation.getMethod().getParameterAnnotations();

		for (int arg = 0; arg < argumentsAnnotations.length; arg++) {
			Annotation[] argumentAnnotations = argumentsAnnotations[arg];

			for (Annotation annotation : argumentAnnotations) {
				if (annotation.annotationType() == UserSession.class) {
					Object argument = invocation.getArguments()[arg];

					if (argument == null || StringUtils.isBlank(argument.toString())) {
						throw new InvalidUserSessionException();
					}
				}
			}
		}

		return invocation.proceed();
	}

}
