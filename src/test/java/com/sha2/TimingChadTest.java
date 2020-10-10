package com.sha2;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimingChadTest {

    @Test

    public void testTiming() {

        int warmup = 100_000;
        Sha256 hasher = new Sha256();
        for(int i = 0; i < warmup; i++) {
            hasher.hash("abc".getBytes());
        }





        int numHashes = 1_000_000;
        Sha256 ourHash = new Sha256();

        //////////////////////////////////////////////
        //////////////////////////////////////////////
        long start = System.currentTimeMillis();
        for (int i = 0; i < numHashes; i++) {
            ourHash.hash("abc".getBytes());
        }


        long end = System.currentTimeMillis();
        //////////////////////////////////////////////
        //////////////////////////////////////////////


    System.out.println(String.format("%d of our hashes took %sms", numHashes, (end - start)));






////////////////////////////////////////////////////////
        Sha256Other theirHash = Sha256Other.getInstance();
        //////////////////////////////////////////////
        //////////////////////////////////////////////
        start = System.currentTimeMillis();
        for (int i = 0; i < numHashes; i++) {
            theirHash.digest("abc".getBytes());
        }
        end = System.currentTimeMillis();
        //////////////////////////////////////////////
        //////////////////////////////////////////////
        // Do stuff measure time

        System.out.println(String.format("%d of their hashes took %sms", numHashes, (end - start)));
    }

}
