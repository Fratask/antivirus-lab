package ru.fratask.lb3.builder;

import lombok.Data;
import ru.fratask.lb2.entity.ObjectContent;
import ru.fratask.lb2.entity.ScanObject;
import ru.fratask.lb2.entity.ScanRegion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class ZipScanObjectBuilder {

    private ScanObject scanObject;

    public ZipScanObjectBuilder(ObjectContent content) {
        if (content.getFileType().equals("zip")) {
            List<ScanRegion> scanRegions = new LinkedList<>();
            List<ScanObject> subObjects = new LinkedList<>();

            for (int i = 0; i < content.getLength()/1024; i++) {
                scanRegions.add(ScanRegion.builder()
                        .length(1024)
                        .offset(i*1024)
                        .objectContent(content)
                        .build());
                subObjects.add(ScanObject.builder()
                        .name(null)
                        .regions(null)
                        .subObjects(null)
                        .build());

            }
            scanObject = ScanObject.builder()
                    .name(content.getFileType())
                    .regions(scanRegions)
                    .subObjects(subObjects)
                    .build();
        }
    }

}
