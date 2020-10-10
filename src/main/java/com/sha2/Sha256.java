package com.sha2;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Sha256 {

    private static int[] H;

    private static int[] K;

    private static final int BLOCK_BITS = 512;
    private static final int BLOCK_BYTES = BLOCK_BITS / 8;

    public Sha256() {
        H = new int[]{
                0x6A09E667, 0xBB67AE85, 0x3C6EF372, 0xA54FF53A, 0x510E527F, 0x9B05688C, 0x1F83D9AB, 0x5BE0CD19
        };

         K = new int[]{
                0x428A2F98, 0x71374491, 0xB5C0FBCF, 0xE9B5DBA5, 0x3956C25B, 0x59F111F1, 0x923F82A4, 0xAB1C5ED5,
                0xD807AA98, 0x12835B01, 0x243185BE, 0x550C7DC3, 0x72BE5D74, 0x80DEB1FE, 0x9BDC06A7, 0xC19BF174,
                0xE49B69C1, 0xEFBE4786, 0x0FC19DC6, 0x240CA1CC, 0x2DE92C6F, 0x4A7484AA, 0x5CB0A9DC, 0x76F988DA,
                0x983E5152, 0xA831C66D, 0xB00327C8, 0xBF597FC7, 0xC6E00BF3, 0xD5A79147, 0x06CA6351, 0x14292967,
                0x27B70A85, 0x2E1B2138, 0x4D2C6DFC, 0x53380D13, 0x650A7354, 0x766A0ABB, 0x81C2C92E, 0x92722C85,
                0xA2BFE8A1, 0xA81A664B, 0xC24B8B70, 0xC76C51A3, 0xD192E819, 0xD6990624, 0xF40E3585, 0x106AA070,
                0x19A4C116, 0x1E376C08, 0x2748774C, 0x34B0BCB5, 0x391C0CB3, 0x4ED8AA4A, 0x5B9CCA4F, 0x682E6FF3,
                0x748F82EE, 0x78A5636F, 0x84C87814, 0x8CC70208, 0x90BEFFFA, 0xA4506CEB, 0xBEF9A3F7, 0xC67178F2,
        };
    }

    // "ch" for Choose
    public int ch(int x, int y, int z) {
        return (x & y) ^ (~x & z);
    }

    // "maj" for Majority
    public int maj(int x, int y, int z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    public int bigSigma0(int x) {
        return rotr(x, 2) ^ rotr(x, 13) ^ rotr(x, 22);
    }

    public int bigSigma1(int x) {
        return rotr(x, 6) ^ rotr(x, 11) ^ rotr(x, 25);
    }

    public int littleSigma0(int x) {
        return rotr(x, 7) ^ rotr(x, 18) ^ (x >>> 3);
    }

    public int littleSigma1(int x) {
        return rotr(x, 17) ^ rotr(x, 19) ^ (x >>> 10);
    }

    public int rotr(int x, int n){
        return (x >>> n) | (x << (32 - n));
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

    public static byte[] padding(byte[] message) {
        //l = the number of bits in the message.
        long l = message.length * 8;
        //k is the number of 0 bits `
        int k = K(l);
        long newLengthBits = l + 1 + k + 64; // Length in bits
        assert newLengthBits % 448 == 64;
        final long newLengthBytes = (newLengthBits / 8);
        assert newLengthBytes <= Integer.MAX_VALUE;

        byte[] newArray = Arrays.copyOf(message, (int) newLengthBytes);

        // Start the index after the last message byte.
        int curIndex = message.length;

        // Append the rest of the k bits.

        int numZeroBytes = (k + 1) / 8;

        for (int i = 0; i < numZeroBytes; i++) {
            if (i == 0) {
                // Append the 1-bit
                newArray[curIndex] = (byte) 0x80;
            } else {
                newArray[curIndex] = (byte) 0x00;
            }
            curIndex++;
        }
        byte[] messageSizeArray = fromUnsignedInt(message.length);
        // Append the last 64 bits (8 bytes)
        for (int i = 0; i < messageSizeArray.length; i++) {
            newArray[curIndex++] = messageSizeArray[i];
        }
        return newArray;
    }

    public static byte[] fromUnsignedInt(long value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putLong(value*8);

        return Arrays.copyOfRange(bytes, 0, 8);
    }

    private static final double BLOCK_SIZE_BITS = 512;

    int T1 = 0;
    int T2 = 0;
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    int e = 0;
    int f = 0;
    int g = 0;
    int h = 0;

    public String hash(byte[] message) {
        byte[] paddedMessage = this.padding(message);
        MessageBlock[] blocks = this.parseMessage(paddedMessage);

        int N = blocks.length; // N <
        for (int i = 0; i < N; i++) {
            MessageSchedule W = new MessageSchedule();
            MessageBlock block = blocks[i];

            // 1.
            int computedWord;
            for (int t = 0; t < 64; t++) {
                if (t < 16) {
                    computedWord = block.getWord(t);
                } else {
                    computedWord = littleSigma1(W.getWord(t - 2)) + W.getWord(t - 7) + littleSigma0(W.getWord(t - 15)) + W.getWord(t - 16);
                }
                W.setWord(t, computedWord);
            }

            // 2. Init variables

            a = H[0];
            b = H[1];
            c = H[2];
            d = H[3];
            e = H[4];
            f = H[5];
            g = H[6];
            h = H[7];

            // 3. Set t and a-h

            for (int t = 0; t < 64; t++) {
                T1 = h + bigSigma1(e) + ch(e, f, g) + K[t] + W.getWord(t);
                T2 = bigSigma0(a) + maj(a, b, c);
                h = g;
                g = f;
                f = e;
                e = d + T1;
                d = c;
                c = b;
                b = a;
                a = T1 + T2;
            }

            // 4. compute the ith hash
            H[0] = a + H[0];
            H[1] = b + H[1];
            H[2] = c + H[2];
            H[3] = d + H[3];
            H[4] = e + H[4];
            H[5] = f + H[5];
            H[6] = g + H[6];
            H[7] = h + H[7];
        }

        Digest digest = new Digest();
        digest.setH0(H[0]);
        digest.setH1(H[1]);
        digest.setH2(H[2]);
        digest.setH3(H[3]);
        digest.setH4(H[4]);
        digest.setH5(H[5]);
        digest.setH6(H[6]);
        digest.setH7(H[7]);

        return digest.toHex();
    }


    private MessageBlock[] parseMessage(byte[] paddedMessage) {
        int totalMessageBits = paddedMessage.length * 8;
        int N = (int) Math.ceil(totalMessageBits / BLOCK_SIZE_BITS);

        MessageBlock[] blocks = new MessageBlock[N];

        for (int i = 0; i < N; i++) { //new messageBlock
            int[] sixteenWords = new int[16];
            for (int x = 0; x < 16; x++) {

                int index = (3*x) + x + 0;

               byte b0 = paddedMessage[index + (16 * i)];
               index = (3*x) + x + 1;
               byte b1 = paddedMessage[index + (16 * i)];
               index = (3*x) + x + 2;
               byte b2 = paddedMessage[index + (16 * i)];
                index = (3*x) + x + 3;
               byte b3 = paddedMessage[index + (16 * i)];

               int t = ((b0 & 0xFF) << 24) | ((b1 & 0xFF) << 16)
                        | ((b2 & 0xFF) << 8) | (b3 & 0xFF);
                sixteenWords[x] = t;
            }
            MessageBlock messageBlock = new MessageBlock(sixteenWords);
            blocks[i] = messageBlock;
        }

        return blocks;
    }
}
