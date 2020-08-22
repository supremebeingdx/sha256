package com.sha2;

public class BinaryArithmatic {
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
}
