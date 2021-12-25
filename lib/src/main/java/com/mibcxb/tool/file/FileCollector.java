package com.mibcxb.tool.file;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class FileCollector implements Runnable {
    private final List<String> sourcePathList;
    private final Consumer<Set<String>> targetConsumer;
    private final Set<String> targetPathList;

    public FileCollector(List<String> sourcePathList, Consumer<Set<String>> targetConsumer) {
        this.sourcePathList = sourcePathList;
        this.targetConsumer = targetConsumer;
        this.targetPathList = new LinkedHashSet<>();
    }

    @Override
    public void run() {
        if (sourcePathList != null && !sourcePathList.isEmpty()) {
            for (String sourcePath : sourcePathList) {
                if (StringUtils.isBlank(sourcePath)) {
                    continue;
                }
                collect(new File(sourcePath));
            }
        }
        targetConsumer.accept(targetPathList);
    }

    private void collect(File file) {
        if (!file.exists()) return;

        if (file.isFile()) {
            targetPathList.add(file.getAbsolutePath());
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File child : files) {
                    collect(child);
                }
            }
        }
    }
}
