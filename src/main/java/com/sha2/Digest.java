package com.sha2;

import com.google.common.io.BaseEncoding;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Digest {
    int H0;
    int H1;
    int H2;
    int H3;
    int H4;
    int H5;
    int H6;
    int H7;

    public int getH0() {
        return H0;
    }

    public void setH0(int h0) {
        H0 = h0;
    }

    public int getH1() {
        return H1;
    }

    public void setH1(int h1) {
        H1 = h1;
    }

    public int getH2() {
        return H2;
    }

    public void setH2(int h2) {
        H2 = h2;
    }

    public int getH3() {
        return H3;
    }

    public void setH3(int h3) {
        H3 = h3;
    }

    public int getH4() {
        return H4;
    }

    public void setH4(int h4) {
        H4 = h4;
    }

    public int getH5() {
        return H5;
    }

    public void setH5(int h5) {
        H5 = h5;
    }

    public int getH6() {
        return H6;
    }

    public void setH6(int h6) {
        H6 = h6;
    }

    public int getH7() {
        return H7;
    }

    public void setH7(int h7) {
        H7 = h7;
    }

    public String toHex() {
        String returnable;
        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(H0).order(ByteOrder.BIG_ENDIAN).array();
        returnable = BaseEncoding.base16().encode(array);

        array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(H1).order(ByteOrder.BIG_ENDIAN).array();
        returnable += BaseEncoding.base16().encode(array);

        array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(H2).order(ByteOrder.BIG_ENDIAN).array();
        returnable += BaseEncoding.base16().encode(array);

        array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(H3).order(ByteOrder.BIG_ENDIAN).array();
        returnable += BaseEncoding.base16().encode(array);

        array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(H4).order(ByteOrder.BIG_ENDIAN).array();
        returnable += BaseEncoding.base16().encode(array);

        array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(H5).order(ByteOrder.BIG_ENDIAN).array();
        returnable += BaseEncoding.base16().encode(array);

        array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(H6).order(ByteOrder.BIG_ENDIAN).array();
        returnable += BaseEncoding.base16().encode(array);

        array = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(H7).order(ByteOrder.BIG_ENDIAN).array();
        returnable += BaseEncoding.base16().encode(array);

        return returnable;
    }
}
