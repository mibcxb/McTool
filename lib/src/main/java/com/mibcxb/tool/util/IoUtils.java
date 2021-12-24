package com.mibcxb.tool.util;

import java.io.*;

public final class IoUtils {
    public static final int BUFFER_SIZE = 64 * 1024;

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int copy(InputStream source, OutputStream target) throws IOException {
        return copy(source, target, BUFFER_SIZE);
    }

    public static int copy(InputStream source, OutputStream target, int bufferSize) throws IOException {
        int size = 0;
        int length;
        byte[] buffer = new byte[bufferSize];
        while ((length = source.read(buffer)) != -1) {
            target.write(buffer, 0, length);
            size += length;
        }
        target.flush();
        return size;
    }

    public static byte[] read(InputStream source) {
        byte[] data = null;
        ByteArrayOutputStream target = null;
        try {
            target = new ByteArrayOutputStream();
            copy(source, target);
            data = target.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(source);
            closeQuietly(target);
        }
        return data;
    }
}
