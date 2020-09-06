package com.sha2;

import com.google.common.base.Preconditions;

public class MessageBlock {

  // Should have 16 ints
  private final int[] words;

  public MessageBlock(int[] words) {
    this.words = words;
    for (int i = 0; i < 16; i++) {
      words[i] = 0;
    }
  }

  public int getWord(int index) {
    Preconditions.checkArgument(index >= 0 && index < 16, "Index must be >= 0 and < 16.");
    return words[index];

  }
}
