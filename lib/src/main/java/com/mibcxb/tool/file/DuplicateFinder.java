package com.mibcxb.tool.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mibcxb.tool.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class DuplicateFinder {
    private final long createTime;
    private final String outputPath;
    private final List<String> sourcePathList;
    private final int multiThreadLimit;
    private final List<String> pendingPathList;
    private final List<String> runningPathList;
    private Consumer<DuplicateReport> reportConsumer;
    private DuplicateReport report;

    private ExecutorService executor;

    private DuplicateFinder(String outputPath, List<String> sourcePathList, int multiThreadLimit) {
        this.createTime = System.currentTimeMillis();
        this.outputPath = outputPath;
        this.sourcePathList = sourcePathList;
        this.multiThreadLimit = multiThreadLimit;
        this.pendingPathList = new ArrayList<>();
        this.runningPathList = new ArrayList<>();
    }

    public void find(Consumer<DuplicateReport> consumer) {
        reportConsumer = consumer;

        executor = Executors.newFixedThreadPool(multiThreadLimit);
        executor.execute(new FileCollector(sourcePathList, this::detect));
    }

    private void detect(Set<String> filePaths) {
        report = new DuplicateReport(createTime, sourcePathList);

        pendingPathList.addAll(filePaths);
        if (pendingPathList.size() > multiThreadLimit) {
            for (int i = 0; i < multiThreadLimit; i++) {
                calcHash();
            }
        } else {
            calcHash();
        }
    }

    private void calcHash() {
        synchronized (pendingPathList) {
            if (runningPathList.size() >= multiThreadLimit) {
                return;
            }
            if (pendingPathList.isEmpty()) {
                return;
            }

            String targetPath = pendingPathList.remove(0);
            if (StringUtils.isBlank(targetPath)) {
                return;
            }
            runningPathList.add(targetPath);
            executor.execute(new FileHashCalc(targetPath, this::onResult));
        }
    }

    private void onResult(Pair<String, String> result) {
        synchronized (pendingPathList) {
            String path = result.getKey();
            String hash = result.getValue();
            if (StringUtils.isNotBlank(hash)) {
                Set<String> pathSet = report.duplicateFiles.computeIfAbsent(hash, k -> new LinkedHashSet<>());
                pathSet.add(path);
            }
            runningPathList.remove(path);

            if (pendingPathList.isEmpty()) {
                if (runningPathList.isEmpty()) {
                    finish();
                }
            } else {
                calcHash();
            }
        }
    }

    private synchronized void finish() {
        executor.shutdown();
        executor = null;

        report.reportTime = System.currentTimeMillis();
        if (reportConsumer != null) {
            reportConsumer.accept(report);
        }
    }

    public long save() {
        if (outputPath == null || report == null) {
            return -1;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(report);
        return FileUtils.saveToFile(new File(outputPath), json, false);
    }

    public static class Builder {
        private String outputPath;
        private int multiThreadLimit = 5;
        private final List<String> sourcePathList = new ArrayList<>();

        public Builder setOutputPath(String outputPath) {
            this.outputPath = outputPath;
            return this;
        }

        public Builder setMultiThreadLimit(int multiThreadLimit) {
            this.multiThreadLimit = multiThreadLimit;
            return this;
        }

        public Builder addSourcePath(String sourcePath) {
            if (StringUtils.isBlank(sourcePath)) {
                return this;
            }
            synchronized (sourcePathList) {
                if (!sourcePathList.contains(sourcePath)) {
                    sourcePathList.add(sourcePath);
                }
            }
            return this;
        }

        public DuplicateFinder build() {
            synchronized (sourcePathList) {
                return new DuplicateFinder(outputPath, sourcePathList, multiThreadLimit);
            }
        }
    }
}
