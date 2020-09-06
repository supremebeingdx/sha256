package com.sha2;

import static com.sha2.BinaryArithmatic.*;

public class Sha256 {
    /**
     * TODO add description
     *
     * @param x The first 32-bit word.
     * @param y The second 32-bit word.
     * @param z The third 32-bit word.
     * @return a new 32-bit word.
     */

    public int ch(int x, int y, int z) {
        return xor(and(x, y), and(bitComp(x), z));
    }

    public int maj(int x, int y, int z) {

        return xor(xor(and(x, y), and(x, z)), and(y, z));
    }

    public int epsilon0(int x) {
        int t7 = rotr(x, 2);
        int t8 = rotr(x, 13);
        int t9 = rotr(x, 22);

        return xor(xor(t7, t8), t9);
    }

    public int epsilon1(int x) {
        int t7 = rotr(x, 6);
        int t8 = rotr(x, 11);
        int t9 = rotr(x, 25);

        return xor(xor(t7, t8), t9);
    }

    public int sigma0(int x) {
        int t7 = rotr(x, 7);
        int t8 = rotr(x, 18);
        int t9 = shr(x, 3);

        return xor(xor(t7, t8), t9);
    }

    public int sigma1(int x) {
        int t7 = rotr(x, 17);
        int t8 = rotr(x, 19);
        int t9 = shr(x, 10);

        return xor(xor(t7, t8), t9);
    }

    /**
     * @param l is the number of bits in a message M.
     * @return k, the smallest, non-negative solution to the equation l + 1 + k is congruent to 448mod512.
     */
    public static int K(long l) {
        // We compute the modulo difference between the number of bits in the message (l) and the number 448.
        int sm = (int) l % 448;
        // We compute the number from sm to 448 and called this k. This is the number of padding bits (minus 1) that we
        // will add to the message in order to pad it. We set k to be one less than what we might imagine, because the
        // padding algorithm always starts with one bit to start the padding, and we don't want to include this twice in
        // k.
        int k = 448 - sm - 1;
        assert (l + 1 + k) % 448 == 0;
        return k;

    }
}
