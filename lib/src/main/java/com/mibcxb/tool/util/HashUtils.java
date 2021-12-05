package com.mibcxb.tool.util;

import com.mibcxb.tool.io.HashStream;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static byte[] md5(byte[] data) {
        return hash(data, HashStream.Algorithm.MD5.algoName);
    }

    public static byte[] sha1(byte[] data) {
        return hash(data, HashStream.Algorithm.SHA1.algoName);
    }

    public static byte[] sha256(byte[] data) {
        return hash(data, HashStream.Algorithm.SHA256.algoName);
    }

    public static byte[] hash(InputStream input, String algorithm) {
        byte[] data = IoUtils.read(input);
        return hash(data, algorithm);
    }

    public static byte[] hash(byte[] data, String algorithm) {
        byte[] hash = new byte[0];
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            hash = digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
