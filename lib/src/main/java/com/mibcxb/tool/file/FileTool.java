package com.mibcxb.tool.file;

import com.mibcxb.tool.util.ByteUtils;
import com.mibcxb.tool.util.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;

import static com.mibcxb.tool.log.MctLog.logger;

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
            byte[] hash = FileUtils.calcSha1(file);
            String text = ByteUtils.bytes2HexStr(hash);
            if (StringUtils.isNotBlank(text)) {
                Set<String> pathSet = dupMap.computeIfAbsent(text, k -> new LinkedHashSet<>());
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
