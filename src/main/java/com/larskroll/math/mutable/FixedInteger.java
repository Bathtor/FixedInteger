/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.larskroll.math.mutable;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * A mutable unsigned integer class with a fixed number of bytes.
 *
 * All arithmetic operations are subject to wrap around!
 *
 * All arithmetic operations are performed in place, and are hence not
 * commutative.
 *
 * @author lkroll
 */
public class FixedInteger extends Number implements Serializable, Comparable<FixedInteger>, Cloneable {

    private static final int INT_MASK = 0xff;

    private final byte[] bytes;

    /**
     * n byte constant 0
     *
     * @param n number of bytes
     * @return
     */
    public static FixedInteger zero(int n) {
        byte[] bytes = new byte[n];
        Arrays.fill(bytes, (byte) 0);
        return new FixedInteger(bytes);
    }

    /**
     * n byte constant 1
     *
     * @param n number of bytes
     * @return
     */
    public static FixedInteger one(int n) {
        byte[] bytes = new byte[n];
        Arrays.fill(bytes, (byte) 0);
        bytes[n - 1] = 1;
        return new FixedInteger(bytes);
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
        byte[] bytes = new byte[n];
        Arrays.fill(bytes, (byte) INT_MASK);
        return new FixedInteger(bytes);
    }

    /**
     * Wrap the given bytes.
     *
     * @param bytes
     */
    public FixedInteger(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * Expose underlying byte array.
     *
     * @return
     */
    public byte[] array() {
        return bytes;
    }

    /**
     * Add num to current value
     *
     * @param num value to add
     */
    public void add(FixedInteger num) {
        if (num.bytes.length != bytes.length) {
            throw new ArithmeticException("Only equal length numbers are currently supported!");
        }
        int sum = 0;
        int index = bytes.length;
        while (index > 0) {
            index--;
            sum = (bytes[index] & INT_MASK) + (num.bytes[index] & INT_MASK) + (sum >>> 8);
            bytes[index] = (byte) sum;
        }
    }

    /**
     * Add num to current value
     *
     * @param num value to add
     */
    public void add(com.larskroll.math.FixedInteger num) {
        add(num.mutable());
    }
    
    /**
     * Subtract num from current value
     * 
     * @param num value to subtract
     */
    public void subtract(FixedInteger num) {
        FixedInteger numCopy = (FixedInteger) num.clone();
        numCopy.neg();
        add(numCopy);
    }
    
    /**
     * Subtract num from current value
     * 
     * @param num value to subtract
     */
    public void subtract(com.larskroll.math.FixedInteger num) {
        subtract(num.mutable());
    }
    
    /**
     * Subtract current value from num
     * 
     * @param num value to subtract from
     */
    public void difference(com.larskroll.math.FixedInteger num) {
        difference(num.mutable());
    }
    
    /**
     * Subtract current value from num
     * 
     * @param num value to subtract from
     */
    public void difference(FixedInteger num) {
        neg();
        add(num);
    }

    /**
     * Gives an immutable value that wraps this value.
     *
     *
     * NOT recommended unless you know what you are doing
     *
     * @return
     */
    public com.larskroll.math.FixedInteger immutable() {
        return new com.larskroll.math.FixedInteger(this);
    }

    /**
     * Gives an immutable value wrapping a copy of this value.
     *
     * @return
     */
    public com.larskroll.math.FixedInteger immutableCopy() {
        return new com.larskroll.math.FixedInteger((FixedInteger) clone());
    }

    /**
     * Increment value by 1
     */
    public void inc() {
        int index = bytes.length;
        int sum = 0x1ff; // pass in 1 as carry 8 bits shifted
        while (index > 0) {
            index--;
            sum = (bytes[index] & INT_MASK) + (sum >>> 8);
            bytes[index] = (byte) sum;
        }
    }
    
    /**
     * Decrement value by 1
     */
    public void decr() {
        add(FixedInteger.max(bytes.length)); // -1 in two complement is the same as max value for unsigned
    }

    /**
     * Replaces current value with its inverse
     * 
     * In other words: flips all bits.
     */
    public void invert() {
        int curval;
        for (int i = 0; i < bytes.length; i++) {
            curval = bytes[i] & INT_MASK;
            bytes[i] = (byte) (curval ^ 0xFF);
        }
    }
    
    void neg() {
        invert();
        inc();
    }

    @Override
    public int intValue() {
        int result = 0;

        for (int i = 4; i >= 0; i--) {
            result = (result << 8) + (getByte(i) & INT_MASK);
        }
        return result;
    }

    private byte getByte(int i) {
        if (i >= bytes.length) {
            return 0;
        }
        return bytes[i];
    }

    @Override
    public long longValue() {
        long result = 0;

        for (int i = 8; i >= 0; i--) {
            result = (result << 8) + (getByte(i) & INT_MASK);
        }
        return result;
    }

    @Override
    public float floatValue() {
        // too lazy to write my own
        return (new BigInteger(1, bytes)).floatValue();
    }

    @Override
    public double doubleValue() {
        // too lazy to write my own
        return (new BigInteger(1, bytes)).floatValue();
    }

    public int compareTo(FixedInteger o) {
        if (bytes.length < o.bytes.length) {
            return -1;
        }
        if (bytes.length > o.bytes.length) {
            return 1;
        }
        for (int i = 0; i < bytes.length; i++) {
            int thisB = bytes[i] & INT_MASK;
            int otherB = o.bytes[i] & INT_MASK;
            if (thisB < otherB) {
                return -1;
            }
            if (thisB > otherB) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FixedInteger) {
            return this.compareTo((FixedInteger) o) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Arrays.hashCode(this.bytes);
        return hash;
    }

    @Override
    public Object clone() {
        byte[] newBytes = Arrays.copyOf(bytes, bytes.length);
        return new FixedInteger(newBytes);
    }

    @Override
    public String toString() {
        return (new BigInteger(1, bytes)).toString();
    }

    public String toString(int radix) {
        return (new BigInteger(1, bytes)).toString(radix);
    }
}
