package ru.fratask.builder;

import lombok.Data;
import ru.fratask.entity.ObjectContent;
import ru.fratask.entity.ScanObject;

@Data
public class ScanObjectBuilder {

    private ScanObject scanObject;

    public ScanObjectBuilder(ObjectContent content) {
        PEScanObjectBuilder peScanObjectBuilder = new PEScanObjectBuilder(content);
        scanObject = peScanObjectBuilder.getScanObject();
        if (scanObject == null) {
            ZipScanObjectBuilder zipScanObjectBuilder = new ZipScanObjectBuilder(content);
            scanObject = zipScanObjectBuilder.getScanObject();
        }
    }

}
