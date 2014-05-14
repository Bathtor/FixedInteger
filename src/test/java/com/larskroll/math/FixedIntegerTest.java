/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.larskroll.math;

import static org.junit.Assert.*;
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
}
