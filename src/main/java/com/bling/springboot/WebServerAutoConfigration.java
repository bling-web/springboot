package com.bling.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName:     
 * @Description:(描述这个类的作用)   
 * @author: 
 * @date:        
 *   
 */
@Configuration
public class WebServerAutoConfigration implements AutoConfigration {

    @Bean
    @BlingConditionOnClass("org.apache.catalina.startup.Tomcat")
    public WebServer tomcatWebServer() {
        return new TomcatWebServer();
    }


    @Bean
    @BlingConditionOnClass("org.eclipse.jetty.util.Jetty")
    public WebServer jettyWebServer() {
        return new JettyWebServer();
    }


}
