package com.mibcxb.tool.io;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashOutputStream extends OutputStream implements HashStream {
    private final OutputStream stream;
    private final MessageDigest digest;

    public HashOutputStream(OutputStream stream) throws NoSuchAlgorithmException {
        this(stream, Algorithm.MD5);
    }

    public HashOutputStream(OutputStream stream, Algorithm algorithm) throws NoSuchAlgorithmException {
        this.stream = stream;
        this.digest = MessageDigest.getInstance(algorithm.algoName);
    }

    @Override
    public byte[] getHash() {
        return digest.digest();
    }

    @Override
    public void write(int b) throws IOException {
        stream.write(b);
        digest.update((byte) b);
    }

    @Override
    public void flush() throws IOException {
        stream.flush();
    }

    @Override
    public void close() throws IOException {
        stream.close();
    }
}
