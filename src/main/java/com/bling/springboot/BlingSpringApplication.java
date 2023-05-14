package com.bling.springboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Map;

/**
 * @ClassName:     
 * @Description:(描述这个类的作用)   
 * @author: 
 * @date:        
 *   
 */

public class BlingSpringApplication {

    public static void run(Class clazz) {
        //注册,启动Spring容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        /**
         * 对spring进行注册,这里为什么要传入一个类?
         *    其实最主要的就是为了能够解析到一个路径,我们知道springboot会默认扫描一个包路径,将这个包路径下面所有需要的bean都注册到spring容器中
         *    那这个路径是怎么确定的呢? 其实就是这个传入类所在的包路径.
         *    会解析这个传入类上的所有注解(包括注解里的注解),然后如果有CompantScan注解的话,就会进行处理
         */
        applicationContext.register(clazz);
        //真正扫描路径,初始化bean的步骤
        applicationContext.refresh();

        startWebServer(applicationContext);


//        startTomcat(applicationContext);


    }

    private static void startWebServer(ApplicationContext applicationContext) {
        Map<String, WebServer> beansOfType = applicationContext.getBeansOfType(WebServer.class);
        for (Map.Entry<String, WebServer> entry : beansOfType.entrySet()) {
            WebServer webServer = entry.getValue();
            webServer.start();
        }
    }

//    /**
//     * 启动tomcat
//     * @param applicationContext
//     */
//    private static void startTomcat(AnnotationConfigWebApplicationContext applicationContext) {
//        Tomcat tomcat = new Tomcat();
//
//        Server server = tomcat.getServer();
//        Service service = server.findService("Tomcat");
//
//        //创建链接,绑定端口
//        Connector connector = new Connector();
//        connector.setPort(8080);
//
//        StandardEngine standardEngine = new StandardEngine();
//        standardEngine.setDefaultHost("localhost");
//
//        StandardHost host = new StandardHost();
//        host.setName("localhost");
//
//        String contextPath = "";
//        StandardContext context = new StandardContext();
//        context.setPath(contextPath);
//        context.addLifecycleListener(new Tomcat.FixContextListener());
//
//        host.addChild(context);
//        standardEngine.addChild(host);
//
//        service.setContainer(standardEngine);
//        service.addConnector(connector);
//
//        /**
//         * 最主要的一步,需要用到DispatcherServlet,这个是SpringMVC里面的,帮我们进行映射
//         * 那DispatcherServlet接收到请求怎么处理的呢?
//         *    DispatcherServlet需要一个applicationContext,也就是spring上下文
//         *    tomcat启动后会将所有的请求交给DispatcherServlet去进行处理,DispatcherServlet拿到请求后会根据请求路径去spring上下文中
//         *    找到对应的bean,然后执行对应的方法,然后将结果进行返回
//         *
//         *
//         */
//            tomcat.addServlet(context,"dispatcher",new DispatcherServlet(applicationContext));
//        context.addServletMappingDecoded("/*","dispatcher");
//
//        try {
//            tomcat.start();
//        } catch (LifecycleException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
