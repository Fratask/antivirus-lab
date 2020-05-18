package ru.fratask.lb3.builder;

import lombok.Data;
import ru.fratask.lb2.entity.ObjectContent;
import ru.fratask.lb2.entity.ScanObject;

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
