package com.sha2;

import com.google.common.base.Preconditions;
import org.immutables.value.internal.$guava$.base.$Preconditions;

import java.util.Objects;

public class BinaryArithmatic {

    public static final int MESSAGE_BLOCK_NUM_BITS = 512;
    public static final int MESSAGE_BLOCK_NUM_WORDS = 16;
    public static final int MESSAGE_BLOCK_WORD_NUM_BITS = 32;

    /**
     * Bitwise AND operation.
     *
     * @param a A boolean representing the first input.
     * @param b A boolean representing the second input.
     * @return {@code true} when both inputs are {@code true}; otherwise {@code false}.
     */
    public static boolean and(boolean a, boolean b) {
        return a && b;
    }

    /**
     * Bitwise OR ("inclusive-OR") operation.
     *
     * @param a A boolean representing the first input.
     * @param b A boolean representing the second input.
     * @return {@code false} when both inputs are {@code false}; otherwise {@code true}.
     */
    public static boolean or(boolean a, boolean b) {
        return a || b;
    }

    /**
     * Bitwise XOR ("exclusive-OR") operation.
     *
     * @param a A boolean representing the first input.
     * @param b A boolean representing the second input.
     * @return {@code true} if the operands are different; otherwise {@code false}.
     */
    public static boolean xor(boolean a, boolean b) {
//        return (!a && b) || (a && !b);
        return a ^ b;
    }

    /**
     * Bitwise complement operation.
     *
     * @param a A boolean representing the first input.
     * @return {@code true} if the operand is {@code false}; otherwise {@code false}.
     */
    public static int bitComp(int a) {
        return ~a;
    }

    /**
     * The rotate left (circular left shift) operation.
     *
     * @param x An integer that is used to hold 32 bits.
     * @param n An integer that is the number of bits to rotate left. The value of n must be <= 0 and < 32.
     * @return the result of the rotate left operation.
     */
    public static int rotl(int x, int n) {
        Preconditions.checkArgument(n >= 0, "n (" + n + ") must be greater than or equal to 0.");
        return (x << n) | (x >>> (32 - n));
    }

    /**
     * The rotate right (circular right shift) operation.
     *
     * @param x An integer that is used to hold 32 bits.
     * @param n An integer that is the number of bits to rotate right. The value of n must be <= 0 and < 32.
     * @return the result of the rotate right operation.
     */
    public static int rotr(int x, int n) {
        Preconditions.checkArgument(n >= 0, "n (" + n + ") must be greater than or equal to 0.");
        return (x >>> n) | (x << (32 - n));

    }

    /**
     * The right shift operation where w = 32.
     *
     * @param x A 32-bit word.
     * @param n An integer that is the number of bits to shift right. The value of n must be <= 0 and < 32.
     * @return the result of the right shift operation.
     */
    public static int rsh32(int x, int n) {
        //: TODO check timing later on...
        Preconditions.checkArgument(n >= 0 && n < 32, "n must be greater than or equal to 0 and less than 32.");
        return x >>> n;
    }

    public static int sigma0(int x) {
        return x;
    }
    public static int sigma1(int x) {
        return x;
    }
    public static int epsilon0(int x) {
        return x;
    }
    public static int epsilon1(int x) {
        return x;
    }

    public static int additionMod2(int x, int y) {
        return x;
    }

//    public static poop digest(Bagel oldBagel, Mustard mustardThatIanSpitIn){
//        Poop poo = // make poop using food.
//        return poop;
//    }
}
