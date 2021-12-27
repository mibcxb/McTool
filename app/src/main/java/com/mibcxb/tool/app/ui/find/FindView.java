package com.mibcxb.tool.app.ui.find;

import com.mibcxb.tool.app.ui.BaseView;

import javax.swing.*;
import java.awt.*;

public class FindView extends BaseView {
    private JLabel labelSrcPath;
    private JList<String> listSource;
    private JButton btnAddPath;
    private JButton btnDelPath;
    private JLabel labelFinder;
    private JButton btnStartFind;
    private JProgressBar pbFindProgress;
    private JLabel labelFindProgress;
    private JLabel labelDupReport;


    @Override
    public void onCreate(JPanel contentView) {
        GridBagLayout layout = newGridBagLayout(10);
        contentView.setLayout(layout);
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
        constraints.gridwidth = GridBagConstraints.RELATIVE;
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

        contentView.add(labelSrcPath);
        contentView.add(listSource);
        contentView.add(btnAddPath);
        contentView.add(btnDelPath);
        contentView.add(labelFinder);
        contentView.add(btnStartFind);
        contentView.add(pbFindProgress);
        contentView.add(labelFindProgress);
        contentView.add(labelDupReport);
    }
}
