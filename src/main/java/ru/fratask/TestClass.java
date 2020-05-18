package ru.fratask;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestClass {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Amir\\Desktop\\zip.zip");
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\Amir\\Desktop\\out.txt");
        int blockSize = 16;
        byte[] data = new byte[blockSize];

        while (inputStream.read(data) != -1) {
            outputStream.write(data);
        }


    }

}
