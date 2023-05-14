package com.bling.springboot;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import java.util.ArrayList;
import java.util.ServiceLoader;

/**
 * @ClassName:     
 * @Description: 该类标识在启动类中,使用Import注解导入,所以Spring会对该类进行加载
 * @author: 
 * @date:        
 *   
 */
public class AutoConfigurationImportSelector implements ImportSelector {
    /**
     * @param importingClassMetadata
     * @return 返回需要加载到Spring容器的全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //这里暂时不引入Spring中的SPI,比较复杂
        //直接使用Java中的SPI
        ArrayList<String> result = new ArrayList<>();
        ServiceLoader<AutoConfigration> load = ServiceLoader.load(AutoConfigration.class);
        for (AutoConfigration autoConfigration : load) {
            result.add(autoConfigration.getClass().getName());
        }
        return result.toArray(new String[0]);
    }
}
