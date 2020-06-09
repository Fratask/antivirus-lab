package ru.fratask.model;

public interface IObjectContent {

    String getPath();

    long getLength();

    int readBlockByPosition(int position, byte[] block);

}
