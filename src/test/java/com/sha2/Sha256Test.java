package com.sha2;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class Sha256Test {

    @Test
    public void testHash() {
       String hash = new Sha256().hash("abc".getBytes());

       byte[] good = BaseEncoding.base16().decode( "BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD");
       byte[] ours = BaseEncoding.base16().decode(hash);

       assertThat(hash).isEqualTo("BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD");

    }

}