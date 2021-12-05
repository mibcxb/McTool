package com.mibcxb.tool.file;

import com.mibcxb.tool.io.HashInputStream;
import com.mibcxb.tool.util.ByteUtils;
import com.mibcxb.tool.util.IoUtils;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static com.mibcxb.tool.log.MctLog.logger;
import static com.mibcxb.tool.log.MctLog.printE;

public class FileTool {

    public Map<String, Set<String>> detectDuplicate(String... paths) {
        File[] files = new File[paths.length];
        for (int i = 0; i < paths.length; i++) {
            files[i] = new File(paths[i]);
        }
        return detectDuplicate(files);
    }

    public Map<String, Set<String>> detectDuplicate(File... files) {
        if (files == null || files.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, Set<String>> dupMap = new HashMap<>();
        for (File file : files) {
            detectDuplicate(file, dupMap);
        }
        return dupMap;
    }

    private void detectDuplicate(File file, Map<String, Set<String>> dupMap) {
        String path = file.getAbsolutePath();
        logger().info("detectDuplicate: {}", path);
        if (file.isFile()) {
            String hashText = null;
            HashInputStream is = null;
            try {
                is = new HashInputStream(new FileInputStream(file));
                byte[] buf = new byte[IoUtils.BUFFER_SIZE];
                int len;
                int size = 0;
                while ((len = is.read(buf)) != -1) {
                    size += len;
                }
                hashText = ByteUtils.bytes2HexStr(is.getHash());
            } catch (NoSuchAlgorithmException | IOException e) {
                printE(e.getMessage(), e);
            } finally {
                IoUtils.closeQuietly(is);
            }

            if (hashText != null) {
                Set<String> pathSet = dupMap.computeIfAbsent(hashText, k -> new LinkedHashSet<>());
                pathSet.add(path);
            }
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File child : files) {
                    detectDuplicate(child, dupMap);
                }
            }
        }
    }
}