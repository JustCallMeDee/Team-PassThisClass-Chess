package GUI;

import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {

    public SettingsDialog(BoardPanel boardPanel){
        setTitle("Settings");
        setSize(300, 300);
        setLayout(new GridLayout(7, 1));

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

        final Color[] light = {Color.WHITE};
        final Color[] dark = {Color.GRAY};

        lightColorBtn.addActionListener(e -> {
            light[0] = JColorChooser.showDialog(this, "Choose Light theme", light[0]);
        });

        darkColorBtn.addActionListener(e -> {
            dark[0] = JColorChooser.showDialog(this, "Choose Dark theme", dark[0]);
        });

        applyBtn.addActionListener(e -> {
            System.out.println(setFirstColor.getSelectedItem() + " " + setFirstColor.getSelectedItem());
            boardPanel.updateColors(light[0], dark[0], (String)(setFirstColor.getSelectedItem()), (String)(setSecondColor.getSelectedItem()));
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