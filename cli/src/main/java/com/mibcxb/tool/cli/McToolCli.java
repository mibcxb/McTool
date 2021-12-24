package com.mibcxb.tool.cli;

import com.mibcxb.tool.file.DuplicateReport;
import com.mibcxb.tool.file.FileTool;

public class McToolCli {
    public static void main(String[] args) {
        FileTool fileTool = new FileTool();
        DuplicateReport report = fileTool.detectDuplicate("/Users/chenxb/Develop/resource/3649-QyCBeRPz");
        fileTool.saveReport(report, "/Users/chenxb/Develop/" + report.reportTime + ".json");
    }
}
