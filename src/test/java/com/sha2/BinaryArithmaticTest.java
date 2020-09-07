package com.sha2;

import com.google.common.primitives.UnsignedInteger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Unit tests for {&link {@link BinaryArithmatic}}.
 */
public class BinaryArithmaticTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testAnd() {
        assertThat(BinaryArithmatic.and(0x00000000, 0x00000000)).isEqualTo(0x00000000);
        assertThat(BinaryArithmatic.and(0x00000000, 0xFFFFFFFF)).isEqualTo(0x00000000);
        assertThat(BinaryArithmatic.and(0xFFFFFFFF, 0x00000000)).isEqualTo(0x00000000);
        assertThat(BinaryArithmatic.and(0xFFFFFFFF, 0xFFFFFFFF)).isEqualTo(0xFFFFFFFF);
    }

    @Test
    public void testOr() {
        assertThat(BinaryArithmatic.or(0x00000000, 0x00000000)).isEqualTo(0x00000000);
        assertThat(BinaryArithmatic.or(0x00000000, 0xFFFFFFFF)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.or(0xFFFFFFFF, 0x00000000)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.or(0xFFFFFFFF, 0xFFFFFFFF)).isEqualTo(0xFFFFFFFF);
    }

    @Test
    public void testXor() {
        assertThat(BinaryArithmatic.xor(0x00000000, 0x00000000)).isEqualTo(0x00000000);
        assertThat(BinaryArithmatic.xor(0x00000000, 0xFFFFFFFF)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.xor(0xFFFFFFFF, 0x00000000)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.xor(0xFFFFFFFF, 0xFFFFFFFF)).isEqualTo(0x00000000);
    }

    @Test
    public void testBitComp() {
        assertThat(BinaryArithmatic.bitComp(0)).isEqualTo(-1);

        assertThat(BinaryArithmatic.bitComp(1)).isEqualTo(-2);

        assertThat(BinaryArithmatic.bitComp(5)).isEqualTo(-6);

        assertThat(BinaryArithmatic.bitComp(10)).isEqualTo(-11);

    }
//    @Test
//    public void testMod2_32() {
//        int zero = 0x00000000;
//        int max = 0xFFFFFFFF;
//        int unknown = max - 1;
//        max = max+1;
//        assertThat(max).isEqualTo(zero);
//    }

    @Test
    public void testRsh32() {
        int x = 0XFFFFFFFF;
        int n = 1;

        assertThat(BinaryArithmatic.shr(x, 0)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.shr(x, 1)).isEqualTo(0x7FFFFFFF);
        assertThat(BinaryArithmatic.shr(x, 2)).isEqualTo(0x3FFFFFFF);
        assertThat(BinaryArithmatic.shr(x, 16)).isEqualTo(0xFFFF);
        assertThat(BinaryArithmatic.shr(x, 31)).isEqualTo(0x1);
    }
     @Test
     public void testRsh32_Minus1() {
         exceptionRule.expect(RuntimeException.class);
         exceptionRule.expectMessage("n must be greater than or equal to 0 and less than 32.");

        int x = 0xFFFFFFFF;
        BinaryArithmatic.shr(x, -1);
    }

    @Test
    public void testRsh32_32() {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("n must be greater than or equal to 0 and less than 32.");

        int x = 0xFFFFFFFF;
        BinaryArithmatic.shr(x, 32);
    }

    @Test
    public void testRotl() {
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFF, 0)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFF, 1)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFF, 2)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFF, 16)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFF, 31)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFF, 32)).isEqualTo(0xFFFFFFFF);

        assertThat(BinaryArithmatic.rotl(0x0, 0)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotl(0x0, 1)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotl(0x0, 2)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotl(0x0, 16)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotl(0x0, 31)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotl(0x0, 32)).isEqualTo(0x0);

        assertThat(BinaryArithmatic.rotl(0x7FFFFFFF, 0)).isEqualTo(0x7FFFFFFF);
        assertThat(BinaryArithmatic.rotl(0x7FFFFFFF, 1)).isEqualTo(0xFFFFFFFE);
        assertThat(BinaryArithmatic.rotl(0x7FFFFFFF, 2)).isEqualTo(0xFFFFFFFD);
        assertThat(BinaryArithmatic.rotl(0x7FFFFFFF, 16)).isEqualTo(0xFFFF7FFF);
        assertThat(BinaryArithmatic.rotl(0x7FFFFFFF, 31)).isEqualTo(0xBFFFFFFF);
        assertThat(BinaryArithmatic.rotl(0x7FFFFFFF, 32)).isEqualTo(0x7FFFFFFF);

        assertThat(BinaryArithmatic.rotl(0xFFFFFFFE, 0)).isEqualTo(0xFFFFFFFE);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFE, 1)).isEqualTo(0xFFFFFFFD);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFE, 2)).isEqualTo(0xFFFFFFFB);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFE, 16)).isEqualTo(0xFFFEFFFF);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFE, 31)).isEqualTo(0x7FFFFFFF);
        assertThat(BinaryArithmatic.rotl(0xFFFFFFFE, 32)).isEqualTo(0xFFFFFFFE);
    }

    @Test
    public void testRotl_Minus1() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("n (-1) must be greater than or equal to 0.");

        int x = 0xFFFFFFFF;
        BinaryArithmatic.rotl(x, -1);
    }

    @Test
    public void testRotr() {
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFF, 0)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFF, 1)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFF, 2)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFF, 16)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFF, 31)).isEqualTo(0xFFFFFFFF);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFF, 32)).isEqualTo(0xFFFFFFFF);

        assertThat(BinaryArithmatic.rotr(0x0, 0)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotr(0x0, 1)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotr(0x0, 2)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotr(0x0, 16)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotr(0x0, 31)).isEqualTo(0x0);
        assertThat(BinaryArithmatic.rotr(0x0, 32)).isEqualTo(0x0);

        assertThat(BinaryArithmatic.rotr(0x7FFFFFFF, 0)).isEqualTo(0x7FFFFFFF);
        assertThat(BinaryArithmatic.rotr(0x7FFFFFFF, 1)).isEqualTo(0xBFFFFFFF);
        assertThat(BinaryArithmatic.rotr(0x7FFFFFFF, 2)).isEqualTo(0xDFFFFFFF);
        assertThat(BinaryArithmatic.rotr(0x7FFFFFFF, 15)).isEqualTo(0xFFFEFFFF);
        assertThat(BinaryArithmatic.rotr(0x7FFFFFFF, 16)).isEqualTo(0xFFFF7FFF);
        assertThat(BinaryArithmatic.rotr(0x7FFFFFFF, 31)).isEqualTo(0xFFFFFFFE);
        assertThat(BinaryArithmatic.rotr(0x7FFFFFFF, 32)).isEqualTo(0x7FFFFFFF);

        assertThat(BinaryArithmatic.rotr(0xFFFFFFFE, 0)).isEqualTo(0xFFFFFFFE);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFE, 1)).isEqualTo(0x7FFFFFFF);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFE, 2)).isEqualTo(0xBFFFFFFF);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFE, 16)).isEqualTo(0xFFFEFFFF);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFE, 31)).isEqualTo(0xFFFFFFFD);
        assertThat(BinaryArithmatic.rotr(0xFFFFFFFE, 32)).isEqualTo(0xFFFFFFFE);
    }

    @Test
    public void testRotr_Minus1() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("n (-1) must be greater than or equal to 0.");

        int x = 0xFFFFFFFF;
        BinaryArithmatic.rotr(x, -1);
    }

    @Test
    public void testEquivalenceRelationship() {
        int x = 0xFFFFFFFF;
        for (int n = 0; n < 32; n++) {
            assertThat(BinaryArithmatic.rotl(x, n)).isEqualTo(BinaryArithmatic.rotr(x, 32-n));
            assertThat(BinaryArithmatic.rotr(x, n)).isEqualTo(BinaryArithmatic.rotl(x, 32-n));
        }
    }

    @Test
    public void testAdditionModulo2() {
        UnsignedInteger x = UnsignedInteger.ONE;
        UnsignedInteger y = UnsignedInteger.MAX_VALUE;

        UnsignedInteger actual = x.plus(y);
        assertThat(actual).isEqualTo(UnsignedInteger.ZERO);
    }

}
