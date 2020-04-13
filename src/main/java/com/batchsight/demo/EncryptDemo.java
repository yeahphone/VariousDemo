package com.batchsight.demo;

import org.junit.*;

import java.security.MessageDigest;

public class EncryptDemo {
  @Test
  void testSHA() throws Exception {
    String message = "Hello World!";
    MessageDigest mDigest;

    mDigest = MessageDigest.getInstance("SHA");
    // 2ef7bde608ce5404e97d5f042f95f89f1c232871
    System.out.println(byteArrayToHex(mDigest.digest(message.getBytes())));

    mDigest = MessageDigest.getInstance("SHA-1");
    // 2ef7bde608ce5404e97d5f042f95f89f1c232871
    System.out.println(byteArrayToHex(mDigest.digest(message.getBytes())));

    mDigest = MessageDigest.getInstance("SHA-256");
    System.out.println(byteArrayToHex(mDigest.digest(message.getBytes())));
  }

  private static String byteArrayToHex(byte[] byteArray) {
    // 首先初始化一个字符数组，用来存放每个16进制字符
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
    char[] resultCharArray = new char[byteArray.length * 2];
    // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
    int index = 0;
    for (byte b : byteArray) {
      resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
      resultCharArray[index++] = hexDigits[b & 0xf];
    }
    // 字符数组组合成字符串返回
    return new String(resultCharArray);
  }
}

