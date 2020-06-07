package ru.fratask.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileObjectContent implements IObjectContent{

    private String path;
    private FileInputStream inputStream;

    public FileObjectContent(String path) {
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.path = path;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public long getLength() {
        return new File(path).length();
    }

    @Override
    public int readBlockByPosition(int position, byte[] block) {
        try {
            return inputStream.read(block, position, block.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
