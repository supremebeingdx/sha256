package com.sha2;

import com.google.common.io.BaseEncoding;
import com.google.common.primitives.UnsignedInteger;
import org.junit.Test;

import static com.sha2.Sha256.K;
import static com.sha2.Sha256.padding;
import static org.assertj.core.api.Assertions.assertThat;


public class Sha256Test {

    @Test
    public void testHash() {
        final String good = "BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD";
        final String cryptii = "BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD";
        assertThat(good).isEqualTo(cryptii);

        String actual = new Sha256().hash("abc".getBytes());
        assertThat(actual).isEqualTo(new Sha256().hash("abc".getBytes())); // identity test first.
        assertThat(actual).isEqualTo(good); // Known good
    }

    @Test
    public void testHashOther() {
        final String good = "BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD";
        final String cryptii = "BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD";
        assertThat(good).isEqualTo(cryptii);

        String actual = BaseEncoding.base16().encode(Sha256Other.getInstance().digest("abc".getBytes()));
        assertThat(actual).isEqualTo(good); // Known good
        assertThat(actual).isEqualTo(new Sha256().hash("abc".getBytes())); // Us
    }

    @Test
    public void testAdditionModulo2() {
        int x = 1;
        int y = -1;

        int actual = x + y;
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void testAdditionModulo() {
        UnsignedInteger x = UnsignedInteger.ONE;
        UnsignedInteger y = UnsignedInteger.MAX_VALUE;

        int actual = x.intValue() + y.intValue();
        assertThat(actual).isEqualTo(0);

        x = UnsignedInteger.valueOf(101);
        y = UnsignedInteger.MAX_VALUE.minus(UnsignedInteger.valueOf(100));

        actual = x.intValue() + y.intValue();
        assertThat(actual).isEqualTo(0);

    }


    @Test
    public void testPadding0() {
        String actual = BaseEncoding.base16().encode(padding(new byte[0]));
        byte[] expectedArr = new byte[64];
        expectedArr[0] = (byte) 0x80;
        String expected = BaseEncoding.base16().encode(expectedArr);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPadding1() {
        String actual = BaseEncoding.base16().encode(padding(new byte[]{0x01, 0x02, 0x03}));
        String expected = "01020380000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000018";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPadding2() {
        byte[] message = new byte[55];
        for (int i = 0; i < message.length; i++) {
            message[i] = 0x03;
        }
        String actual = BaseEncoding.base16().encode(padding(message));
        String expected = "030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303038000000000000001B8";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPadding3() {
        byte[] message = new byte[56];
        for (int i = 0; i < message.length; i++) {
            message[i] = 0x03;
        }
        String actual = BaseEncoding.base16().encode(padding(message));
        String expected =
                "0303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303" +
                        "8000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                        "00000000000001C0";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPadding4() {
        byte[] message = new byte[57];
        for (int i = 0; i < message.length; i++) {
            message[i] = 0x03;
        }
        String actual = BaseEncoding.base16().encode(padding(message));
        String expected =
                "0303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303" +
                        "0380000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                        "00000000000001C8";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPadding5() {
        byte[] message = new byte[64];
        for (int i = 0; i < message.length; i++) {
            message[i] = 0x03;
        }
        String actual = BaseEncoding.base16().encode(padding(message));
        String expected =
                "" +
                        "03030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303" +
                        "8000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000200";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testPadding6() {
        byte[] message = new byte[120];
        for (int i = 0; i < message.length; i++) {
            message[i] = 0x03;
        }
        String actual = BaseEncoding.base16().encode(padding(message));
        String expected =
                "0303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303" +
                        "0303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303" +
                        "0303030303030303800000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000" +
                        "00000000000003C0";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testKWhereLEqualsZero() {
        assertThat(K(0)).isEqualTo(447);
        assertThat(K(1)).isEqualTo(446);
        assertThat(K(446)).isEqualTo(1);
        assertThat(K(447)).isEqualTo(0);
        assertThat(K(448)).isEqualTo(447);
        assertThat(K(449)).isEqualTo(446);
        assertThat(K(512)).isEqualTo(383);

    }
}