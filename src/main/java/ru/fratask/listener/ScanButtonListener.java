package ru.fratask.listener;

import ru.fratask.entity.FileObjectContentImpl;
import ru.fratask.builder.ScanObjectBuilder;
import ru.fratask.utils.ScanEngine;
import ru.fratask.gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScanButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = Window.getInstance().getFilePathTextField().getText();
        ScanEngine.getInstance().scan(new ScanObjectBuilder(new FileObjectContentImpl(path)).getScanObject());
    }

}
