package ru.fratask.lb2.entity;

public interface ObjectContent {

    String getFileType();

    Long getLength();

    int read(int position, byte[] data);
}
