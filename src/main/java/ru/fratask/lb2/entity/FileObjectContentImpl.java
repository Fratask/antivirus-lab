package ru.fratask.lb2.entity;

import lombok.SneakyThrows;

import java.io.FileInputStream;

public class FileObjectContentImpl implements ObjectContent {

    private FileInputStream input;
    private String fileType;

    @SneakyThrows
    public FileObjectContentImpl(String path) {
        input = new FileInputStream(path);
        fileType = path.split("\\.")[path.split("\\.").length-1];
    }

    @Override
    public String getFileType() {
        return fileType;
    }

    @SneakyThrows
    @Override
    public Long getLength() {
        return input.getChannel().size();
    }

    @SneakyThrows
    @Override
    public int read(int position, byte[] data) {
        return input.read(data, position, data.length);
    }

}
