/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.st;

import java.util.Random;

/**
 *
 * @author jeff.huang
 */
public abstract class MyBean {

    public int update() {
        return createRandom().nextInt();
    }

    protected abstract Random createRandom();
}
