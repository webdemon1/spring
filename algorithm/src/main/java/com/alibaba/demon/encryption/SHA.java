package com.alibaba.demon.encryption;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * sha 256
 * @author: Demon
 * @create: 2019-04-17
 **/
public class SHA {

    @Test
    public void testSha() throws NoSuchAlgorithmException {
        String originalString = "taylor";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encryptHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        String hash = bytesToHex(encryptHash);
        System.out.println(hash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }


    /*
     * 哈希函数的概念非常宽泛，是一类算法的统称，常见的哈希函数有MD5、SHA等

     SHA（Secure Hash Algorithm，安全散列算法）是一个密码散列函数家族，由美国国家安全局（NSA）设计，
     是美国的政府标准。包括 SHA-0系列、SHA-1系列、SHA-2系列和SHA-3系列。
     SHA-256是SHA-2系列函数之一,对于SHA-256:
     1.无论输入多长，都输出64个字符，共32字节（byte），256位（bit）
     2.输出只包含数字0~9和字母A~F，大小写不敏感

     */
}
