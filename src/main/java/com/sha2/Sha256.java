package com.sha2;

import static com.sha2.BinaryArithmatic.additionMod2;
import static com.sha2.BinaryArithmatic.and;
import static com.sha2.BinaryArithmatic.bitComp;
import static com.sha2.BinaryArithmatic.rotr;
import static com.sha2.BinaryArithmatic.shr;
import static com.sha2.BinaryArithmatic.xor;

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

  private static final int BLOCK_SIZE_BITS = 512;

  int T1;
  int T2;
  int a;
  int b;
  int c;
  int d;
  int e;
  int f;
  int g;
  int h;
  int H0;
  int H1;
  int H2;
  int H3;
  int H4;
  int H5;
  int H6;
  int H7;

  public byte[] hash(byte[] message) {
    byte[] paddedMessage = pad(message);
    MessageBlock[] blocks = this.parseMessage(paddedMessage);

    preprocess();
    int N = blocks.length; // N <
    for (int i = 1; i < N; i++) {
      MessageSchedule W = new MessageSchedule();
      MessageBlock block = blocks[i];

      // 1.
      int computedWord;
      for (int t = 0; t < 64; t++) {
        if (t < 16) {
          computedWord = block.getWord(t);
        } else {
          int foo = additionMod2(
              epsilon1(W.getWord(t - 2)),
              W.getWord(t - 7)
          );
          int bar = additionMod2(
              epsilon0(t - 15),
              W.getWord(t - 16)
          );
          computedWord = additionMod2(foo, bar);
        }
        W.setWord(t, computedWord);
      }

      // 2. Init variables

      // 3. Set t and a-h

      // 4. compute the ith hash



    }

    return null;
  }

  private void preprocess() {
    H0 = Constants.H[0];
  }

  private byte[] pad(byte[] message) {
    //TODO Fill in when we get home
    return null;
  }

  private MessageBlock[] parseMessage(byte[] paddedMessage) {
    int totalMessageBits = paddedMessage.length * 8;
    int N = totalMessageBits / BLOCK_SIZE_BITS;
    int m = N / 8;

    MessageBlock[] blocks = new MessageBlock[N];

    for (int i = 0; i < m; i++) { //new messageBlock
      int[] sixteenWords = new int[16];
      for (int x = 0; x < 16; x++) {
        sixteenWords[x] = paddedMessage[x + (16 * i)];
      }
      MessageBlock messageBlock = new MessageBlock(sixteenWords);
      blocks[i] = messageBlock;
    }

    return blocks;
  }
}
