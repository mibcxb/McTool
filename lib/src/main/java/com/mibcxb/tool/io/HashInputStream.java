package com.mibcxb.tool.io;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashInputStream extends InputStream implements HashStream {
    private final InputStream stream;
    private final MessageDigest digest;

    public HashInputStream(InputStream stream) throws NoSuchAlgorithmException {
        this(stream, Algorithm.MD5);
    }

    public HashInputStream(InputStream stream, Algorithm algorithm) throws NoSuchAlgorithmException {
        this.stream = stream;
        this.digest = MessageDigest.getInstance(algorithm.algoName);
    }

    @Override
    public byte[] getHash() {
        return digest.digest();
    }

    @Override
    public int read() throws IOException {
        int b = stream.read();
        digest.update((byte) b);
        return b;
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
