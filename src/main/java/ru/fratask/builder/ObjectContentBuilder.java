package ru.fratask.builder;

import ru.fratask.model.FileObjectContent;
import ru.fratask.model.FileType;
import ru.fratask.model.IObjectContent;

public class ObjectContentBuilder {

    public static IObjectContent build(String path) {
        if (FileType.getFileType(path).equals("exe")) {
            return new FileObjectContent(path);
        }

        return null;
    }
}
