package com.mibcxb.tool.util;

public class ByteUtils {
    private static final char[] HEX_CHAR_ARRAY = "0123456789abcdef".toCharArray();

    public static String byte2HexStr(byte b) {
        char[] buffer = new char[2];
        buffer[0] = HEX_CHAR_ARRAY[b >>> 4 & 0xF];
        buffer[1] = HEX_CHAR_ARRAY[b & 0xF];
        return new String(buffer);
    }

    public static String bytes2HexStr(byte[] bytes) {
        if (bytes == null) return "";
        StringBuilder buffer = new StringBuilder();
        for (byte b : bytes) {
            buffer.append(byte2HexStr(b));
        }
        return buffer.toString();
    }

    public static byte[] hexStr2Bytes(String hexStr) {
        char[] chars = hexStr.toCharArray();
        byte[] bytes = new byte[chars.length >> 1];
        for (int i = 0; i < bytes.length; i++) {
            int h = Character.digit(chars[i * 2], 16);
            int l = Character.digit(chars[i * 2 + 1], 16);
            bytes[i] = (byte) (h << 4 | l);
        }
        return bytes;
    }
}
