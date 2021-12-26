package com.mibcxb.tool.app.ui.main;

import com.mibcxb.tool.app.ui.BaseView;

import javax.swing.*;
import java.awt.*;

public class MainView extends BaseView {
    private JTabbedPane pTabs;
    private JPanel pDuplicate;
    private JLabel labelSrcPath;
    private JList<String> listSource;
    private JButton btnAddPath;
    private JButton btnDelPath;
    private JLabel labelFinder;
    private JButton btnStartFind;
    private JProgressBar pbFindProgress;
    private JLabel labelFindProgress;
    private JLabel labelDupReport;

    private JPanel pMarkFiles;
    private JPanel pCopyFiles;

    @Override
    public void onCreate(JPanel contentView) {
        initCards();
        initTools();

        BorderLayout borderLayout = new BorderLayout();
        contentView.setLayout(borderLayout);
        contentView.add(pTabs, BorderLayout.CENTER);
    }

    private void initTools() {
        pTabs = new JTabbedPane();
        pTabs.addTab("查找重复", pDuplicate);
        pTabs.addTab("标记文件", pMarkFiles);
        pTabs.addTab("复制文件", pCopyFiles);
    }

    private void initCards() {
        initDuplicate();
        initMarkFiles();
        initCopyFiles();
    }

    private void initDuplicate() {
        GridBagLayout layout = newGridBagLayout(10);
        pDuplicate = new JPanel(layout);
        GridBagConstraints constraints;

        labelSrcPath = new JLabel("文件路径");
        labelSrcPath.setHorizontalAlignment(SwingConstants.CENTER);
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.addLayoutComponent(labelSrcPath, constraints);

        listSource = new JList<>();
        listSource.setBorder(BorderFactory.createLineBorder(new Color(224, 224, 224)));
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        layout.addLayoutComponent(listSource, constraints);

        btnAddPath = new JButton("添加");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        layout.addLayoutComponent(btnAddPath, constraints);

        btnDelPath = new JButton("删除");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        layout.addLayoutComponent(btnDelPath, constraints);

        labelFinder = new JLabel("查找重复");
        labelFinder.setHorizontalAlignment(SwingConstants.CENTER);
        constraints = new GridBagConstraints();
        constraints.gridy = 3;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.addLayoutComponent(labelFinder, constraints);

        btnStartFind = new JButton("开始");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        layout.addLayoutComponent(btnStartFind, constraints);

        pbFindProgress = new JProgressBar();
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.addLayoutComponent(pbFindProgress, constraints);

        labelFindProgress = new JLabel("--");
        constraints = new GridBagConstraints();
        constraints.gridy = 4;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.addLayoutComponent(labelFindProgress, constraints);

        labelDupReport = new JLabel("结果报告");
        labelDupReport.setHorizontalAlignment(SwingConstants.CENTER);
        constraints = new GridBagConstraints();
        constraints.gridy = 5;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.addLayoutComponent(labelDupReport, constraints);

        pDuplicate.add(labelSrcPath);
        pDuplicate.add(listSource);
        pDuplicate.add(btnAddPath);
        pDuplicate.add(btnDelPath);
        pDuplicate.add(labelFinder);
        pDuplicate.add(btnStartFind);
        pDuplicate.add(pbFindProgress);
        pDuplicate.add(labelFindProgress);
        pDuplicate.add(labelDupReport);
    }

    private void initMarkFiles() {
        pMarkFiles = new JPanel();
    }

    private void initCopyFiles() {
        pCopyFiles = new JPanel();
    }
}
