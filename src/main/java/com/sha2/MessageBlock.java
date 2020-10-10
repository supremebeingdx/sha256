package com.sha2;

import com.google.common.base.Preconditions;

public class MessageBlock {

  // Should have 16 ints
  private final int[] words;

  public MessageBlock(int[] words) {
    Preconditions.checkArgument(words.length >= 0 && words.length <= 16, "Words.length must be >= 0 and < 16.");
    this.words = words;
  }

  public int getWord(int index) {
    Preconditions.checkArgument(index >= 0 && index < 16, "Index must be >= 0 and < 16.");
    return words[index];

  }
}
