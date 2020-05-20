package ru.fratask.entity;

public interface ObjectContent {

    String getFileType();

    Long getLength();

    int read(int position, byte[] data);
}
