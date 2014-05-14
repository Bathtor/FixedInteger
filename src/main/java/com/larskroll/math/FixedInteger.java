/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.larskroll.math;

import java.io.Serializable;

/**
 *
 * @author lkroll
 */
public class FixedInteger extends Number implements Serializable, Comparable<FixedInteger>, Cloneable {

    private final com.larskroll.math.mutable.FixedInteger val;

    public static FixedInteger zero(int n) {
        return new FixedInteger(com.larskroll.math.mutable.FixedInteger.zero(n));
    }

    public static FixedInteger one(int n) {
        return new FixedInteger(com.larskroll.math.mutable.FixedInteger.one(n));
    }

    public static FixedInteger max(int n) {
        return new FixedInteger(com.larskroll.math.mutable.FixedInteger.max(n));
    }

    public FixedInteger(com.larskroll.math.mutable.FixedInteger val) {
        this.val = val;
    }

    public FixedInteger(byte[] bytes) {
        val = new com.larskroll.math.mutable.FixedInteger(bytes);
    }

    public FixedInteger add(FixedInteger num) {
        FixedInteger newMe = (FixedInteger) this.clone();
        newMe.val.add(num.val);
        return newMe;
    }

    public FixedInteger inc() {
        FixedInteger newMe = (FixedInteger) this.clone();
        newMe.val.inc();
        return newMe;
    }

    @Override
    public int intValue() {
        return val.intValue();
    }

    @Override
    public long longValue() {
        return val.longValue();
    }

    @Override
    public float floatValue() {
        return val.floatValue();
    }

    @Override
    public double doubleValue() {
        return val.doubleValue();
    }

    public int compareTo(FixedInteger o) {
        return val.compareTo(o.val);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FixedInteger) {
            return val.equals(((FixedInteger) o).val);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.val != null ? this.val.hashCode() : 0);
        return hash;
    }

    @Override
    public Object clone() {
        return new FixedInteger((com.larskroll.math.mutable.FixedInteger) val.clone());
    }

    @Override
    public String toString() {
        return val.toString();
    }

    public String toString(int radix) {
        return val.toString(radix);
    }

}
