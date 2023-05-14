package com.bling.springboot;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName:     
 * @Description:(描述这个类的作用)   
 * @author: 
 * @date:        
 *   
 */  
public class  OnClassCondition implements Condition {

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(BlingConditionOnClass.class.getName());
        List<Object> classPathList = attributes.get("value");
        if(classPathList == null || classPathList.isEmpty()){
            return false;
        }
        try {
            Objects.requireNonNull(context.getClassLoader()).loadClass((String) classPathList.get(0));
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
