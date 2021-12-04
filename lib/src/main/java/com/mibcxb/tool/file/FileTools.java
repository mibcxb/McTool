package com.mibcxb.tool.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.mibcxb.tool.log.MctLog.logger;

public class FileTools {

    public void detectDuplicate(String path) {
        detectDuplicate(new File(path));
    }

    public void detectDuplicate(File file) {
        detectDuplicate(file, new HashMap<>());
    }

    private void detectDuplicate(File file, Map<String, Set<String>> dupMap) {
        logger().info("detectDuplicate: {}", file.getAbsolutePath());
    }
}
