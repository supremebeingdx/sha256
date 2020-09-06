package com.sha2;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HashingTest {

    @Test
    public void testPadding0() {
        String actual = BaseEncoding.base16().encode(Hashing.padding(new byte[0]));
        byte[] expectedArr = new byte[64];
        expectedArr[0] = (byte) 0x80;
        String expected = BaseEncoding.base16().encode(expectedArr);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPadding1() {
        String actual = BaseEncoding.base16().encode(Hashing.padding(new byte[0]));
        byte[] expectedArr = new byte[64];
        expectedArr[0] = (byte) 0x80;
        String expected = BaseEncoding.base16().encode(expectedArr);
        assertThat(actual).isEqualTo(expected);
    }
}