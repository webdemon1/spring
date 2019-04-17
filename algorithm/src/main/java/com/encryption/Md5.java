package com.encryption;

import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  md5
 * @author: Demon
 * @create: 2019-04-17
 **/
public class Md5 {

    @Test
    public void md5() throws NoSuchAlgorithmException {
        String passWord = "taylor";
        String encryptedPass = "7D8BC5F1A8D3787D06EF11C97D4655DF";
        MessageDigest messageDigest = MessageDigest.getInstance("md5");
        messageDigest.update(passWord.getBytes());
        byte[] digest = messageDigest.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        assertEquals(encryptedPass,myHash);
    }

    /*
     * MD5，Message Digest Algorithm 5，是一种被广泛使用的信息摘要算法，
     * 可以将给定的任意长度数据通过一定的算法计算得出一个 128 位固定长度的散列值。MD5 具有如下特点：

     1.压缩性：任意长度的原数据，其 MD5 值都是固定的，即 128 位；
     2.易计算：计算原数据的 MD5 值是一个比较容易的过程；
     3.抗修改：原数据的任意改动，所得到的 MD5 值都是迥然不同的；
     4.防碰撞：MD5 使用的是散列函数（也称哈希函数），一定概率上也存在哈希冲突（也称哈希碰撞），
     即多个不同的原数据对应一个相同的 MD5 值。
     5. 不可逆
     */
}
