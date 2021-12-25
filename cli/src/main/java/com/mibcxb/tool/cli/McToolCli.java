package com.mibcxb.tool.cli;

import com.mibcxb.tool.file.DuplicateFinder;

import java.util.concurrent.CountDownLatch;

public class McToolCli {
    public static void main(String[] args) {
        DuplicateFinder finder = new DuplicateFinder.Builder()
                .setMultiThreadLimit(5)
                .setOutputPath("")
                .addSourcePath("")
                .build();

        CountDownLatch countDownLatch = new CountDownLatch(1);
        finder.find(report -> {
            System.out.println(report.duplicateFiles.size());
            countDownLatch.countDown();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long size = finder.save();
        System.out.println(size);
    }
}
