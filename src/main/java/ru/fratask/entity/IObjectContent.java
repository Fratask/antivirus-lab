package ru.fratask.entity;

public interface IObjectContent {

    String getPath();

    long getLength();

    int readBlockByPosition(int position, byte[] block);

}
