/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springmbean;

import java.io.IOException;
import javax.management.MalformedObjectNameException;
import mx4j.tools.adaptor.http.HttpAdaptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.ObjectNameManager;

/**
 *
 * @author jeff.huang
 */
public class Main {

    public static void main(String[] args)
            throws IOException,
                MalformedObjectNameException, Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] { "springmbean/beanRefJMXServer.xml", });

        //启动 HttpAdaptor，mx4j的HttpAdaptor真讨厌，要手工启动
        HttpAdaptor httpAdaptor = (HttpAdaptor)ctx.getBean("httpAdaptor");
        httpAdaptor.start();

//        ConnectorServerFactoryBean conn = (ConnectorServerFactoryBean)ctx.getBean("serverConnector");

        //动态注册一个MBean的例子
        /*MBeanExporter exporter = (MBeanExporter) ctx.getBean("exporter");
        exporter.registerManagedResource(new Mbean1(),
                ObjectNameManager.getInstance("ZtManager:name=mbean"));*/
    }  
}
