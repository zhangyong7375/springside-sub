/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jeff
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[] {"com/springioc/app.xml"});
        MyBean [] beans = {
            (MyBean) ctx.getBean("myBean1"),(MyBean) ctx.getBean("myBean2"),(MyBean) ctx.getBean("myBean3"),
        };

        for(MyBean b : beans){
            b.sayHello();
        }

    }

}
