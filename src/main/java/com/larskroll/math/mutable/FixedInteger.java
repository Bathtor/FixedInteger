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
 * Provides unsigned integer of fixed number of bytes
 *
 * @author lkroll
 */
public class FixedInteger extends Number implements Serializable, Comparable<FixedInteger>, Cloneable {

    private static final int INT_MASK = 0xff;

    private final byte[] bytes;

    public static FixedInteger zero(int n) {
        byte[] bytes = new byte[n];
        Arrays.fill(bytes, (byte) 0);
        return new FixedInteger(bytes);
    }

    public static FixedInteger one(int n) {
        byte[] bytes = new byte[n];
        Arrays.fill(bytes, (byte) 0);
        bytes[n - 1] = 1;
        return new FixedInteger(bytes);
    }

    public static FixedInteger max(int n) {
        byte[] bytes = new byte[n];
        Arrays.fill(bytes, (byte) INT_MASK);
        return new FixedInteger(bytes);
    }

    public FixedInteger(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] array() {
        return bytes;
    }

    /**
     * Add in place
     * @param num
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
     * Increment by 1 in place
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
