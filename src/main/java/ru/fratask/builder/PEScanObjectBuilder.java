package ru.fratask.builder;

import lombok.Data;
import ru.fratask.entity.ObjectContent;
import ru.fratask.entity.ScanObject;
import ru.fratask.entity.ScanRegion;

import java.util.ArrayList;
import java.util.List;

@Data
public class PEScanObjectBuilder {

    private ScanObject scanObject;

    public PEScanObjectBuilder(ObjectContent content) {
        if (content.getFileType().equals("exe")) {

            List<ScanRegion> scanRegions = new ArrayList<>();

            for (int i = 0; i < content.getLength()/1024 + 1; i++) {
                scanRegions.add(ScanRegion.builder()
                        .length(1024)
                        .offset(0)
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
