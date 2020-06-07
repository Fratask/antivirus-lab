package ru.fratask.builder;

import ru.fratask.entity.FileObjectContent;
import ru.fratask.entity.FileType;
import ru.fratask.entity.IObjectContent;

public class ObjectContentBuilder {

    public static IObjectContent build(String path) {
        if (FileType.getFileType(path).equals("exe")) {
            return new FileObjectContent(path);
        }

        return null;
    }
}
