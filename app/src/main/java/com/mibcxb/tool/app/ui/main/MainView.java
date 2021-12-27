package com.mibcxb.tool.app.ui.main;

import com.mibcxb.tool.app.ui.BaseView;
import com.mibcxb.tool.app.ui.copy.CopyView;
import com.mibcxb.tool.app.ui.find.FindView;
import com.mibcxb.tool.app.ui.mark.MarkView;

import javax.swing.*;
import java.awt.*;

public class MainView extends BaseView {
    private JTabbedPane pTabs;
    private FindView findView;
    private MarkView markView;
    private CopyView copyView;

    @Override
    public void onCreate(JPanel contentView) {
        findView = new FindView();
        markView = new MarkView();
        copyView = new CopyView();

        pTabs = new JTabbedPane();
        pTabs.addTab("查找重复", findView.create());
        pTabs.addTab("标记文件", markView.create());
        pTabs.addTab("复制文件", copyView.create());

        BorderLayout borderLayout = new BorderLayout();
        contentView.setLayout(borderLayout);
        contentView.add(pTabs, BorderLayout.CENTER);
    }
}
