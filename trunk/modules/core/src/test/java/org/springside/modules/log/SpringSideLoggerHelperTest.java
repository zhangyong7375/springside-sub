/**
 * Copyright (c) 2005-2010 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * $Id$
 */
package org.springside.modules.log;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;

/**
 *
 * @author jeff.huang
 */
public class SpringSideLoggerHelperTest {

    public SpringSideLoggerHelperTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getLoggerForCurrentClass method, of class SpringSideLoggerHelper.
     */
    @Test
    public void testGetLoggerForCurrentClass() {
        System.out.println("getLoggerForCurrentClass");
        Logger result = SpringSideLoggerHelper.getLoggerForCurrentClass();
        System.out.println("logger name = " + result.getName());
        assertEquals(result.getName(), "org.springside.modules.log.SpringSideLoggerHelperTest");        
    }

}