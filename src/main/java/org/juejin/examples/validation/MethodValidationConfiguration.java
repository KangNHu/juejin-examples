package org.juejin.examples.validation;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validator;

/**
*  注册{@link MyMethodValidationPostProcessor}
* @author : KangNing Hu
*/
@Configuration
@ConditionalOnClass(MethodValidationConfiguration.class)
public class MethodValidationConfiguration {

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor(Environment environment,
	                                                                   @Lazy Validator validator){
		MethodValidationPostProcessor processor = new MyMethodValidationPostProcessor();
		boolean proxyTargetClass = environment.getProperty("spring.aop.proxy-target-class", Boolean.class, true);
		processor.setProxyTargetClass(proxyTargetClass);
		processor.setValidator(validator);
		return processor;

	}

}
