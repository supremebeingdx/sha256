package com.sha2;



import java.nio.ByteBuffer;
import java.util.Arrays;

import static com.sha2.Sha256.*;
public class Hashing {
    public static byte[] sha256(byte[] message) {
        padding(message);

    //: TODO return a 32 byte value.
        return null;
    }

    public static byte[] padding(byte[] message) {
        long l = message.length * 8;
        int k = K(l);
        long newLength = l + 1 + k + 64;
        assert newLength <= Integer.MAX_VALUE;
        byte[] newArray = Arrays.copyOf(message, (int) newLength);

        byte[] paddingArray = new byte[64];
        paddingArray[0] = (byte) 0x80;
        for(int i = 1; i < 55; i++) {
            paddingArray[i] = 0x00;
        }

        byte[] lengthBytes = fromUnsignedInt(message.length);

        for(int i = 56; i < 64; i++){
            paddingArray[i] = lengthBytes[i-56];
        }
        return paddingArray;
    }

    public static byte[] fromUnsignedInt(int value)
    {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putInt(value);

        return Arrays.copyOfRange(bytes, 0, 8);
    }
}
