package com.mibcxb.tool.cli;

import com.mibcxb.tool.file.FileTools;

public class McToolCli {
    public static void main(String[] args) {
        FileTools fileTools = new FileTools();
        fileTools.detectDuplicate("");
    }
}
