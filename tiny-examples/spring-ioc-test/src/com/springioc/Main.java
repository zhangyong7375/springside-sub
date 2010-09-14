/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
        usingClassPathResource();
        usingFileSystemResource();
    }

    private static void usingApplicationContext() throws BeansException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"com/springioc/app.xml"});
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

}