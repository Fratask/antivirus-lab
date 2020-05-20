package ru.fratask.gui;

import lombok.Data;
import ru.fratask.listener.ScanButtonListener;

import javax.swing.*;
import java.awt.*;

@Data
public class Window extends JFrame {

    private JTextField filePathTextField = new JTextField();
    private JButton scanButton = new JButton("scan");

    private static volatile Window instance;

    public static Window getInstance() {
        if (instance == null) {
            synchronized(Window.class) {
                if (instance == null) {
                    instance = new Window();
                }
            }
        }
        return instance;
    }

    public Window() {
        setSize(200,100);
        setLocation(860,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
        scanButton.addActionListener(new ScanButtonListener());
        add(filePathTextField);
        add(scanButton);
    }
}
