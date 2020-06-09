package ru.fratask.model;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ScanRegion {

    private int size;
    private int offset;
    private IObjectContent objectContent;
    private final static int standardRegionSize = 256;

    public int readBlock(byte[] block) {
        return objectContent.readBlockByPosition(offset, block);
    }

    public static int getStandardRegionSize() {
        return standardRegionSize;
    }

    public static List<ScanRegion> buildList(IObjectContent content) {
        List<ScanRegion> regions = new ArrayList<>();
        long countOfRegions = content.getLength() % getStandardRegionSize() == 0 ? content.getLength() / getStandardRegionSize() : (content.getLength() / getStandardRegionSize()) + 1;
        for (long i = 0; i < countOfRegions; i++) {
            ScanRegion region = ScanRegion.builder()
                    .size(getStandardRegionSize())
                    .offset(0)
                    .objectContent(content)
                    .build();
            regions.add(region);
        }
        return regions;
    }
}
