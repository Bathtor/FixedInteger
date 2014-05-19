/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.larskroll.math;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author lkroll
 */
@RunWith(JUnit4.class)
public class FixedIntegerTest {

    private static final int BYTES = 20;

    @Test
    public void additionTest() {
        for (int i = 1; i < BYTES; i++) {
            FixedInteger zero = FixedInteger.zero(i);
            FixedInteger one = FixedInteger.one(i);
            byte[] twoB = new byte[i];
            twoB[i-1] = 2;
            FixedInteger two = new FixedInteger(twoB);
            assertEquals(zero.add(one), one);
            assertEquals(one.add(zero), one);
            assertEquals(one.add(one), two);
            FixedInteger max = FixedInteger.max(i);
            assertEquals(max.add(zero), max);
            assertEquals(max.add(one), zero);
        }
    }
    
    @Test
    public void incrementTest() {
        for (int i = 1; i < BYTES; i++) {
            FixedInteger zero = FixedInteger.zero(i);
            FixedInteger one = FixedInteger.one(i);
            byte[] twoB = new byte[i];
            twoB[i-1] = 2;
            FixedInteger two = new FixedInteger(twoB);
            assertEquals(zero.inc(), one);
            assertEquals(one.inc(), two);
            FixedInteger max = FixedInteger.max(i);
            assertEquals(max.inc(), zero);
        }
    }
    
    @Test
    public void subtractTest() {
        for (int i = 1; i < BYTES; i++) {
            FixedInteger zero = FixedInteger.zero(i);
            FixedInteger one = FixedInteger.one(i);
            byte[] twoB = new byte[i];
            twoB[i-1] = 2;
            FixedInteger two = new FixedInteger(twoB);
            assertEquals(one.subtract(one), zero);
            assertEquals(two.subtract(one), one);
            FixedInteger max = FixedInteger.max(i);
            assertEquals(zero.subtract(one), max);
        }
    }
    
    @Test
    public void decrementTest() {
        for (int i = 1; i < BYTES; i++) {
            FixedInteger zero = FixedInteger.zero(i);
            FixedInteger one = FixedInteger.one(i);
            byte[] twoB = new byte[i];
            twoB[i-1] = 2;
            FixedInteger two = new FixedInteger(twoB);
            assertEquals(one.decr(), zero);
            assertEquals(two.decr(), one);
            FixedInteger max = FixedInteger.max(i);
            assertEquals(zero.decr(), max);
        }
    }
    
    private void assertEquals(Object is, Object expected) {
        Assert.assertEquals(expected, is); // because I'm bad and do it wrong way around every time -.-
    }
}
