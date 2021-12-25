package com.mibcxb.tool.file;

import com.mibcxb.tool.util.ByteUtils;
import com.mibcxb.tool.util.FileUtils;
import com.mibcxb.tool.util.HashUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.function.Consumer;

public class FileHashCalc implements Runnable {
    private final String algorithm;
    private final String sourcePath;
    private final Consumer<Pair<String, String>> consumer;

    public FileHashCalc(String sourcePath, Consumer<Pair<String, String>> consumer) {
        this(HashUtils.ALGO_SHA_1, sourcePath, consumer);
    }

    public FileHashCalc(String algorithm, String sourcePath, Consumer<Pair<String, String>> consumer) {
        this.algorithm = algorithm;
        this.sourcePath = sourcePath;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        File file = new File(sourcePath);
        String text = null;
        if (file.exists() && file.isFile()) {
            byte[] hash = FileUtils.calcHash(file, algorithm);
            text = ByteUtils.bytes2HexStr(hash);
        }
        consumer.accept(new ImmutablePair<>(sourcePath, text));
    }
}
