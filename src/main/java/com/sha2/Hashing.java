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
        long newLengthBits = l + 1 + k; // Length in bits
        long newLengthBytes = (newLengthBits / 8) + 8;
        assert newLengthBytes <= Integer.MAX_VALUE;
        byte[] newArray = Arrays.copyOf(message, (int) newLengthBytes);

        byte[] paddingArray = fromUnsignedInt(message.length);
        paddingArray[0] = (byte) (paddingArray[0] | 0x80); // First bit is 1

        // Append the last 64 bits.
        for(int i = 0; i < 8; i++){
            newArray[(int)newLengthBytes - 8 + i] = paddingArray[i];
        }

        return newArray;
    }

    public static byte[] fromUnsignedInt(long value)
    {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putLong(value);

        return Arrays.copyOfRange(bytes, 0, 8);
    }
}
