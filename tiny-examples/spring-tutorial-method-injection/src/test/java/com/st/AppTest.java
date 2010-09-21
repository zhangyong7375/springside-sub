/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.st;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.Assert.*;

/**
 *
 * @author jeff.huang
 */
public class AppTest {
    private ApplicationContext ctx;

    public AppTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("app-context.xml");
    }

    @After
    public void tearDown() {
    }

    
    /**
     * Test of methodInject method, of class App.
     */
    @Test
    public void testMethodInject() {
        System.out.println("methodInject");
        App instance = new App(ctx);
        int x1 = instance.methodInject();
        int x2 = instance.methodInject();
        assertFalse(x1 == x2);
    }

}