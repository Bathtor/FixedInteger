/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.larskroll.math;

import java.io.Serializable;

/**
 * An immutable unsigned integer class with a fixed number of bytes.
 * 
 * All arithmetic operations are subject to wrap around!
 * 
 * @author lkroll
 */
public class FixedInteger extends Number implements Serializable, Comparable<FixedInteger>, Cloneable {

    private final com.larskroll.math.mutable.FixedInteger val;

    /**
     * n byte constant 0
     * 
     * @param n number of bytes
     * @return 
     */
    public static FixedInteger zero(int n) {
        return new FixedInteger(com.larskroll.math.mutable.FixedInteger.zero(n));
    }

    /**
     * n byte constant 1
     * 
     * @param n number of bytes
     * @return 
     */
    public static FixedInteger one(int n) {
        return new FixedInteger(com.larskroll.math.mutable.FixedInteger.one(n));
    }

    /**
     * Max value for n bytes
     * 
     * In other words: n bytes of 0xFF
     * 
     * @param n number of bytes
     * @return 
     */
    public static FixedInteger max(int n) {
        return new FixedInteger(com.larskroll.math.mutable.FixedInteger.max(n));
    }

    /**
     * Create immutable FixedInteger by wrapping a mutable FixedInteger
     * 
     * @param val 
     */
    public FixedInteger(com.larskroll.math.mutable.FixedInteger val) {
        this.val = val;
    }

    /**
     * Wrap the given bytes.
     * 
     * @param bytes 
     */
    public FixedInteger(byte[] bytes) {
        val = new com.larskroll.math.mutable.FixedInteger(bytes);
    }

    /**
     * Add num and return immutable result.
     * 
     * Does not change the current value.
     * 
     * @param num value to add to current value
     * @return 
     */
    public FixedInteger add(FixedInteger num) {
        FixedInteger newMe = (FixedInteger) this.clone();
        newMe.val.add(num.val);
        return newMe;
    }
    
    /**
     * Subtract num and return immutable result.
     * 
     * Does not change the current value.
     * 
     * @param num to add to the current value
     * @return 
     */
    public FixedInteger subtract(FixedInteger num) {
        FixedInteger newMe = (FixedInteger) this.clone();
        newMe.val.subtract(num.val);
        return newMe;
    }
    
    /**
     * Add num and return immutable result.
     * 
     * Does not change the current value.
     * 
     * @param num value to add to current value
     * @return 
     */
    public FixedInteger add(com.larskroll.math.mutable.FixedInteger num) {
        FixedInteger newMe = (FixedInteger) this.clone();
        newMe.val.add(num);
        return newMe;
    }
    
    /**
     * Subtract num and return immutable result.
     * 
     * Does not change the current value.
     * 
     * @param num to add to the current value
     * @return 
     */
    public FixedInteger subtract(com.larskroll.math.mutable.FixedInteger num) {
        FixedInteger newMe = (FixedInteger) this.clone();
        newMe.val.subtract(num);
        return newMe;
    }
    
    /**
     * Give access to the underlying mutable value.
     * 
     * NOT recommended unless you know what you are doing!
     * 
     * @return 
     */
    public com.larskroll.math.mutable.FixedInteger mutable() {
        return val;
    }
    
    /**
     * Gives a mutable copy of this value.
     * 
     * @return 
     */
    public com.larskroll.math.mutable.FixedInteger mutableCopy() {
        return (com.larskroll.math.mutable.FixedInteger) val.clone();
    }
    
    /**
     * Gives a copy of the underlying byte array.
     * 
     * @return 
     */
    public byte[] array() {
        return ((com.larskroll.math.mutable.FixedInteger) val.clone()).array();
    }

    /**
     * Increment value.
     * @return A new value that is one bigger than the current value.
     */
    public FixedInteger inc() {
        FixedInteger newMe = (FixedInteger) this.clone();
        newMe.val.inc();
        return newMe;
    }
    
    /**
     * Decrement value.
     * @return A new value that is one smaller than the current value.
     */
    public FixedInteger decr() {
        FixedInteger newMe = (FixedInteger) this.clone();
        newMe.val.decr();
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
