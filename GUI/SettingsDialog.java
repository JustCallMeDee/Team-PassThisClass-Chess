package gui;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {

    public SettingsDialog(BoardPanel boardPanel){
        setTitle("Settings");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));

        JButton lightColorBtn = new JButton("Change Light theme");
        JButton darkColorBtn = new JButton("Change Dark theme");
        JButton applyBtn = new JButton("Apply");

        final Color[] light = {Color.WHITE};
        final Color[] dark = {Color.GRAY};

        lightColorBtn.addActionListener(e -> {
            light[0] = JColorChooser.showDialog(this, "Choose Light theme", light[0]);
        });

        darkColorBtn.addActionListener(e -> {
            dark[0] = JColorChooser.showDialog(this, "Choose Dark theme", dark[0]);
        });

        applyBtn.addActionListener(e -> {
            boardPanel.updateColors(light[0], dark[0]);
        });

        add(lightColorBtn);
        add(darkColorBtn);
        add(applyBtn);

        setVisible(true);
    }
}