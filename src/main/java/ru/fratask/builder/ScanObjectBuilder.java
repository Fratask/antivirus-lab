package ru.fratask.builder;

import ru.fratask.model.FileType;
import ru.fratask.model.IObjectContent;
import ru.fratask.model.ScanObject;

public class ScanObjectBuilder {

    public static ScanObject build(IObjectContent content) {
        if (content != null) {
            if (content.getPath() != null) {
                String fileType = FileType.getFileType(content);
                if (fileType.equals("zip")) {
                    return ZipScanObjectBuilder.build(content);
                } else if (fileType.equals("exe")) {
                    return PEScanObjectBuilder.build(content);
                } else {
                    return null;
                }
            }
        }
        return null;
    }

}
