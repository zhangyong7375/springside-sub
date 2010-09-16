/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jsr303;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.Validator;

/**
 *
 * @author jeff.huang
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("com/jsr303/app.xml");
        Validator validator = ctx.getBean("validator", Validator.class);
        Email email = ctx.getBean("email", Email.class);
        System.out.println("email.from = " + email.getFrom());
        try{
            validator.validate(email, null);
        }catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.SEVERE, null, ex);
        }
    }
}
