/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jeff.huang
 */
@Configuration
public class MyAppConfig {
    private @Value("#{random.nextInt()}") int value;

    @Bean
    public MyBean fooBean() {
        return new MyBean("My-Annotation-Bean + " + value);
    }
}
