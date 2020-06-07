package ru.fratask.builder;

import ru.fratask.entity.ScanObject;
import ru.fratask.entity.ScanRegion;
import ru.fratask.entity.IObjectContent;

import java.util.List;

public class PEScanObjectBuilder {

    public static ScanObject build(IObjectContent content) {
        List<ScanRegion> scanRegions = ScanRegion.buildList(content);
        ScanObject scanObject = ScanObject.builder()
                .name(content.getPath())
                .scanRegions(scanRegions)
                .subObjects(null)
                .build();
        return scanObject;
    }


}
