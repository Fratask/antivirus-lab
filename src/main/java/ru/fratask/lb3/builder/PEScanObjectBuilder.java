package ru.fratask.lb3.builder;

import lombok.Data;
import ru.fratask.lb2.entity.ObjectContent;
import ru.fratask.lb2.entity.ScanObject;
import ru.fratask.lb2.entity.ScanRegion;

import java.util.ArrayList;
import java.util.List;

@Data
public class PEScanObjectBuilder {

    private ScanObject scanObject;

    public PEScanObjectBuilder(ObjectContent content) {
        if (content.getFileType().equals("exe")) {

            List<ScanRegion> scanRegions = new ArrayList<>();

            for (int i = 0; i < content.getLength()/1024; i++) {
                scanRegions.add(ScanRegion.builder()
                        .length(1024)
                        .offset(i*1024)
                        .objectContent(content)
                        .build());
            }
            scanObject = ScanObject.builder()
                    .name(content.getFileType())
                    .regions(scanRegions)
                    .subObjects(null)
                    .build();
        }
    }

}
