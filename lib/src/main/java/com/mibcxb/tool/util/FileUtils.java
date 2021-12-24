package com.mibcxb.tool.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    public static long saveToFile(File file, String text, boolean append) {
        byte[] data = text == null ? null : text.getBytes(StandardCharsets.UTF_8);
        return saveToFile(file, data, append);
    }

    public static long saveToFile(File file, byte[] data, boolean append) {
        if (file == null || data == null) return -1;

        long size = 0;
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new ByteArrayInputStream(data);
            os = new FileOutputStream(file, append);
            size = IoUtils.copy(is, os);
        } catch (Throwable tr) {
            tr.printStackTrace();
        } finally {
            IoUtils.closeQuietly(is);
            IoUtils.closeQuietly(os);
        }
        return size;
    }
}
