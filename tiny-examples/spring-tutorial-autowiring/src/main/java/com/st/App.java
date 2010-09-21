package com.st;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("app-context.xml");
        MyBean2 b2 = ac.getBean("myBean2", MyBean2.class);

        System.out.println(b2.getMyBean().getS());

        ac = new ClassPathXmlApplicationContext("app2-context.xml");
        b2 = ac.getBean("myBean2", MyBean2.class);
        System.out.println(b2.getMyBean().getS());
    }
}
