package com.bling.springboot;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName:     
 * @Description:(描述这个类的作用)   
 * @author: 
 * @date:        
 *   
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnClassCondition.class)
public @interface BlingConditionOnClass {
    String value();
}
