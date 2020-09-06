package com.sha2;

import static com.sha2.BinaryArithmatic.additionMod2;
import static com.sha2.BinaryArithmatic.epsilon0;
import static com.sha2.BinaryArithmatic.epsilon1;

public class Sha256 {

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
