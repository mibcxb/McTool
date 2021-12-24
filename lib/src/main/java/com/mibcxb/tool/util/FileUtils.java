package com.mibcxb.tool.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class FileUtils {
    public static byte[] calcSha1(File file) {
        return calcHash(file, HashUtils.ALGO_SHA_1);
    }

    public static byte[] calcHash(File file, String algorithm) {
        if (file == null) return null;
        if (!file.exists()) return null;
        if (!file.isFile()) return null;

        byte[] hash = null;
        FileInputStream is = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            is = new FileInputStream(file);
            byte[] buffer = new byte[IoUtils.BUFFER_SIZE];
            int length;
            while ((length = is.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            hash = md.digest();
        } catch (Throwable tr) {
            tr.printStackTrace();
        } finally {
            IoUtils.closeQuietly(is);
        }
        return hash;
    }
}
