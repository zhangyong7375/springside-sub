/*
 * Copyright 2010 Torokina Networks Pty Ltd
 */

package com.mycompany.findbugtest.bug;

/**
 *
 * @author jeff.huang
 */
public class Bug {
    public static final String [] HELLO = {
        "Hello", "World"
    };

    public String getHello() {
        return HELLO[1];
    }
    

}
