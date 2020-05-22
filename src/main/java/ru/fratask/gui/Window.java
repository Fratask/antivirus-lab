package ru.fratask.gui;

import lombok.Data;
import ru.fratask.listener.ScanButtonListener;

import javax.swing.*;
import java.awt.*;

@Data
public class Window extends JFrame {

    private JTextField filePathTextField = new JTextField();
    private JButton scanButton = new JButton("scan");
    private JPanel configPanel = new JPanel();
    private JPanel statPanel = new JPanel();
    private JLabel reportLabel = new JLabel("Scan report");
    private JLabel virusesCountLabel = new JLabel("Count of viruses: n/a");
    private JLabel filesCountLabel = new JLabel("Count of files: n/a");
    private JLabel objectsCountLabel = new JLabel("Count of files: n/a");
    private JLabel virusNameLabel = new JLabel("Viruses names: n/a");



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
        setSize(600,800);
        setLocation(200,100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));
        scanButton.addActionListener(new ScanButtonListener());
        configPanel.add(filePathTextField);
        configPanel.add(scanButton);
        configPanel.setSize(600,200);
        configPanel.setLayout(new GridLayout(2, 1));

        statPanel.add(reportLabel);
        statPanel.add(filesCountLabel);
        statPanel.add(objectsCountLabel);
        statPanel.add(virusesCountLabel);
        statPanel.add(virusNameLabel);
        statPanel.setLayout(new GridLayout(5,1));
        statPanel.setSize(600,600);
        add(statPanel);
        add(configPanel);
    }
}
