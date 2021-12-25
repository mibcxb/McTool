package com.mibcxb.tool.file;

import java.util.*;

public class DuplicateReport {
    public long createTime;
    public long reportTime;
    public List<String> sourcePathList;
    public Map<String, Set<String>> duplicateFiles = new HashMap<>();

    public DuplicateReport(long createTime, List<String> sourcePathList) {
        this.createTime = createTime;
        this.sourcePathList = sourcePathList;
    }
}
