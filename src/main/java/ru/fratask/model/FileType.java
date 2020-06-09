package ru.fratask.model;

import java.io.*;
import java.util.Arrays;

public enum FileType {
    ZIP("zip", "PK"),
    EXE("exe", "MZ");

    private String type;
    private String sequence;

    FileType(String type, String sequence) {
        this.type = type;
        this.sequence = sequence;
    }

    public static String getFileType(IObjectContent content) {
        byte[] fileTypeByte = new byte[2];
        content.readBlockByPosition(0, fileTypeByte);
        for (FileType fileType: FileType.values()) {
            if (compareFileTypeData(fileType, fileTypeByte)) {
                return fileType.type;
            }
        }
        return "unknown";
    }

    public static String getFileType(String path) {
        if (new File(path).isDirectory()) {
            return "dir";
        }
        byte[] fileTypeByte = new byte[2];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (inputStream != null) {
                inputStream.read(fileTypeByte, 0, fileTypeByte.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (FileType fileType: FileType.values()) {
            if (compareFileTypeData(fileType, fileTypeByte)) {
                return fileType.type;
            }
        }
        return "unknown";
    }

    private static boolean compareFileTypeData(FileType fileType, byte[] data) {
        return Arrays.equals(fileType.sequence.getBytes(), data);
    }

}
