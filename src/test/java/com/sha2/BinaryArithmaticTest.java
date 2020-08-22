package com.sha2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Unit tests for {&link {@link BinaryArithmatic}}.
 */
public class BinaryArithmaticTest {

    @Test
    public void testAnd() {
        boolean result = BinaryArithmatic.and(false,false);
        assertThat(result).isFalse();

        result = BinaryArithmatic.and(true, true);
        assertThat(result).isTrue();

        result = BinaryArithmatic.and(false, true);
        assertThat(result).isFalse();

        result = BinaryArithmatic.and(true, false);
        assertThat(result).isFalse();
    }

    @Test
    public void testOr() {
        assertThat(BinaryArithmatic.or(false,false)).isFalse();

        assertThat(BinaryArithmatic.or(true,true)).isTrue();

        assertThat(BinaryArithmatic.or(false,true)).isTrue();

        assertThat(BinaryArithmatic.or(true,false)).isTrue();
    }

    @Test
    public void testXor() {
        assertThat(BinaryArithmatic.xor(false,false)).isFalse();

        assertThat(BinaryArithmatic.xor(true,true)).isFalse();

        assertThat(BinaryArithmatic.xor(false,true)).isTrue();

        assertThat(BinaryArithmatic.xor(true,false)).isTrue();
    }
}
