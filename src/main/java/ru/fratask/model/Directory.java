package ru.fratask.model;

import lombok.Data;
import ru.fratask.builder.ObjectContentBuilder;
import ru.fratask.builder.ScanObjectBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class Directory {

    private String path;
    private int countOfFiles;
    private List<ScanObject> scanObjects;

    public static Directory build(String path) {
        File dir = new File(path);
        if (!dir.isDirectory()) {
            return null;
        }
        File[] files = dir.listFiles();

        List<ScanObject> scanObjects = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                IObjectContent content = ObjectContentBuilder.build(file.getPath());
                if (content != null) {
                    scanObjects.add(ScanObjectBuilder.build(content));
                }
            }
        }

        Directory directory = new Directory();
        directory.setPath(path);
        directory.setCountOfFiles(scanObjects.size());
        directory.setScanObjects(scanObjects);
        return directory;
    }

}
