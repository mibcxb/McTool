package com.mibcxb.tool.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mibcxb.tool.util.ByteUtils;
import com.mibcxb.tool.util.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class FileTool {

    public DuplicateReport detectDuplicate(String... paths) {
        File[] files = new File[paths.length];
        for (int i = 0; i < paths.length; i++) {
            files[i] = new File(paths[i]);
        }
        return detectDuplicate(files);
    }

    public DuplicateReport detectDuplicate(File... files) {
        DuplicateReport report = new DuplicateReport();
        if (files != null && files.length > 0) {
            for (File file : files) {
                report.sourcePathList.add(file.getAbsolutePath());
                detectDuplicate(file, report.duplicateFiles);
            }
        }
        return report;
    }

    private void detectDuplicate(File file, Map<String, Set<String>> dupMap) {
        String path = file.getAbsolutePath();
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

    public long saveReport(DuplicateReport report, String filepath) {
        String json = null;
        if (report != null) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            json = gson.toJson(report);
        }
        return FileUtils.saveToFile(new File(filepath), json, false);
    }
}
