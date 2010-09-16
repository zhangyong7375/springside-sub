/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springioc;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 *
 * @author Jeff
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        usingApplicationContext();
        usingFileSystemXmlApplicationContext();
        usingClassPathResource();
        usingFileSystemResource();
        usingAnnotationApplicationContext();
    }

    private static void usingApplicationContext() throws BeansException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"com/springioc/app.xml"});
        MyBean[] beans = {(MyBean) ctx.getBean("myBean1"), (MyBean) ctx.getBean("myBean2"), (MyBean) ctx.getBean("myBean3")};
        for (MyBean b : beans) {
            b.sayHello();
        }
    }

    private static void usingFileSystemXmlApplicationContext() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[] {
           "src/com/springioc/app.xml"
        });
        MyBean[] beans = {(MyBean) ctx.getBean("myBean1"), (MyBean) ctx.getBean("myBean2"), (MyBean) ctx.getBean("myBean3")};
        for (MyBean b : beans) {
            b.sayHello();
        }
    }

    private static void usingClassPathResource() {
        ClassPathResource resource = new ClassPathResource("com/springioc/app.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        MyBean[] beans = { (MyBean)factory.getBean("myBean1"), (MyBean)factory.getBean("myBean2"),
            (MyBean)factory.getBean("myBean3") };
        for(MyBean b : beans){
            b.sayHello();
        }
    }

    private static void usingFileSystemResource() {
        FileSystemResource resource =
                new FileSystemResource("src/com/springioc/app.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        MyBean[] beans = { (MyBean)factory.getBean("myBean1"), (MyBean)factory.getBean("myBean2"),
            (MyBean)factory.getBean("myBean3") };
        for(MyBean b : beans){
            b.sayHello();
        }
    }

    private static void usingAnnotationApplicationContext() {
        ApplicationContext ctx = null;

        try{
            ctx = new AnnotationConfigApplicationContext(MyAppConfig.class);
        }catch(BeanCreationException ex){
            Logger.getAnonymousLogger().log(Level.SEVERE, null, ex);
        }

        ctx = new ClassPathXmlApplicationContext("com/springioc/app-anno.xml");
        MyBean bean = ctx.getBean("fooBean", MyBean.class);
        bean.sayHello();
    }

}
