package com.mibcxb.tool.file;

import java.util.*;

public class DuplicateReport {
    public final long reportTime = System.currentTimeMillis();
    public final List<String> sourcePathList = new ArrayList<>();
    public final Map<String, Set<String>> duplicateFiles = new HashMap<>();
}
