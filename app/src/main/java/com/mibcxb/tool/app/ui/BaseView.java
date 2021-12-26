package com.mibcxb.tool.app.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public abstract class BaseView {
    private JPanel pContent;

    public final JPanel create() {
        if (pContent == null) {
            pContent = new JPanel();
        }
        onCreate(pContent);
        return pContent;
    }

    public abstract void onCreate(JPanel contentView);

    protected GridBagLayout newGridBagLayout(int rows) {
        return newGridBagLayout(rows, 12);
    }

    protected GridBagLayout newGridBagLayout(int rows, int columns) {
        GridBagLayout layout = new GridBagLayout();
        if (rows > 0) {
            layout.rowHeights = new int[rows];
            Arrays.fill(layout.rowHeights, 0);
            layout.rowWeights = new double[rows];
            Arrays.fill(layout.rowWeights, 1d / rows);
        }
        if (columns > 0) {
            layout.columnWidths = new int[columns];
            Arrays.fill(layout.columnWidths, 0);
            layout.columnWeights = new double[columns];
            Arrays.fill(layout.columnWeights, 1d / columns);
        }
        return layout;
    }
}
