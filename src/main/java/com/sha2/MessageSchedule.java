package com.sha2;

import com.google.common.base.Preconditions;

public class MessageSchedule {

  // Should have 16 ints
  private final int[] words;

  public MessageSchedule() {
    this.words = new int[64];
    for (int i = 0; i < 64; i++) {
      words[i] = 0;
    }
  }

  public int getWord(int index) {
    Preconditions.checkArgument(index >= 0 && index < 64, "Index must be >= 0 and < 64.");
    return words[index];
  }

  public void setWord(int index, int word) {
    Preconditions.checkArgument(index >= 0 && index < 64, "Index must be >= 0 and < 64.");
    this.words[index] = word;
  }
}
