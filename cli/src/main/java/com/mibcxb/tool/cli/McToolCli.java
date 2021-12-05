package com.mibcxb.tool.cli;

import com.mibcxb.tool.file.FileTool;

import java.util.Map;
import java.util.Set;

public class McToolCli {
    public static void main(String[] args) {
        FileTool fileTool = new FileTool();
        Map<String, Set<String>> dupMap = fileTool.detectDuplicate("");
        System.out.println(dupMap.size());
    }
}
