package org.juejin.examples.validation;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.validation.annotation.Validated;

import javax.validation.Constraint;
import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * 方法级别的匹配器
* @author : KangNing Hu
*/
public class MyAnnotationMatchingPointcut  implements Pointcut {

	private ClassFilter classFilter;


	private MethodMatcher methodMatcher;



	public MyAnnotationMatchingPointcut(){
		this.classFilter = ClassFilter.TRUE;
		this.methodMatcher = new AnnotationMethodMatcher();
	}

	@Override
	public ClassFilter getClassFilter() {
		return this.classFilter;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return this.methodMatcher;
	}

	private class AnnotationMethodMatcher implements MethodMatcher{
		public 	boolean matches(Method method, Class<?> targetClass){
			Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			// 校验方法参数上是否有 jsr 380标准注解 或者 spring Validation标准注解
			for (Annotation[] annotations : parameterAnnotations){
				if (isValidationAnnotation(annotations)){
					return true;
				}
			}
			//校验方法上是否有 jsr 380标准注解 或者 spring Validation标准注解
			Annotation[] methodAnnotations = method.getAnnotations();
			if (isValidationAnnotation(methodAnnotations)){
				return true;
			}
			//校验方法返回参数上是否有 jsr 380标准注解 或者 spring Validation标准注解
			AnnotatedType annotatedReturnType = method.getAnnotatedReturnType();
			Annotation[] annotations = annotatedReturnType.getAnnotations();
			return isValidationAnnotation(annotations);
		}


		public boolean isRuntime(){
			return false;
		}

		public 	boolean matches(Method method, Class<?> targetClass, Object... args){
			return false;
		}
	}


	/**
	 * 校验是否为bean 校验注解
	 * @param annotation 需要校验的注解
	 * @return  当注解为jsr 380标准注解 或者 spring Validation标准注解时返回true否则false
	 */
	private  boolean isValidationAnnotation(Annotation annotation){
		Class<? extends Annotation> type = annotation.annotationType();
		return type.isAssignableFrom(Validated.class)|| type.isAssignableFrom(Valid.class) ||type.getAnnotation(Constraint.class) != null;
	}


	/**
	 * 校验是否为bean 校验注解
	 * @param annotations 需要校验的注解l列表
	 * @return  当注解列表中存在jsr 380标准注解 或者 spring Validation标准注解时返回true否则false
	 */
	private  boolean isValidationAnnotation(Annotation[] annotations){
		if (annotations == null){
			return false;
		}
		for (Annotation annotation : annotations){
			if (isValidationAnnotation(annotation)){
				return true;
			}
		}
		return false;
	}

}
