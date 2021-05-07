package org.juejin.examples.validation;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
* 对{@link MethodValidationPostProcessor}的一个扩展
* @author : KangNing Hu
*/
public class MyMethodValidationPostProcessor  extends MethodValidationPostProcessor {


	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		if (this.advisor instanceof DefaultPointcutAdvisor){
			DefaultPointcutAdvisor advisor = (DefaultPointcutAdvisor) (DefaultPointcutAdvisor) this.advisor;
			advisor.setPointcut(new MyAnnotationMatchingPointcut());
		}

	}

}
