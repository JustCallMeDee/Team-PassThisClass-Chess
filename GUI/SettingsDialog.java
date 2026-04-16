package GUI;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {
    Color light, dark;

    public SettingsDialog(BoardPanel boardPanel){
        setTitle("Settings");
        setSize(300, 300);
        setLayout(new GridLayout(7, 1));

        light = boardPanel.getLightColor();
        dark = boardPanel.getDarkColor();

        JButton lightColorBtn = new JButton("Change Light theme");
        JButton darkColorBtn = new JButton("Change Dark theme");
        JTextField firstColorLabel = new JTextField("Color for Player 1:");
        JComboBox setFirstColor = new JComboBox<String>(new String[]
            {"White", "Blue", "Blue-Gray", "Cyan", "Gray", "Green", 
             "Orange", "Pink", "Purple", "Red", "Black", "Yellow"});
        JTextField secondColorLabel = new JTextField("Color for Player 2:");
        JComboBox setSecondColor = new JComboBox<String>(new String[]
            {"Black", "Blue", "Blue-Gray", "Cyan", "Gray", "Green", 
             "Orange", "Pink", "Purple", "Red", "White", "Yellow"});
        JButton applyBtn = new JButton("Apply");

        lightColorBtn.addActionListener(e -> {
            light = JColorChooser.showDialog(this, "Choose Light theme", light);
        });

        darkColorBtn.addActionListener(e -> {
            dark = JColorChooser.showDialog(this, "Choose Dark theme", dark);
        });

        applyBtn.addActionListener(e -> {
            //System.out.println(setFirstColor.getSelectedItem() + " " + setFirstColor.getSelectedItem());
            boardPanel.updateColors(light, dark, (String)(setFirstColor.getSelectedItem()), (String)(setSecondColor.getSelectedItem()));
        });

        add(lightColorBtn);
        add(darkColorBtn);
        add(firstColorLabel);
        add(setFirstColor);
        add(secondColorLabel);
        add(setSecondColor);
        add(applyBtn);

        setVisible(true);
    }
}