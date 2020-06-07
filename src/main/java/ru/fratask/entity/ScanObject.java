package ru.fratask.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ScanObject {

    private String name;
    private List<ScanRegion> scanRegions;
    private List<ScanObject> subObjects;

    public int readBlock(byte[][] blocks, ScanObject parentObject) {
        int readBytes = 0;
        for (int i = 0; i < scanRegions.size(); i++) {
            readBytes += scanRegions.get(i).readBlock(blocks[i]);
        }
        //TODO: Придумать что надо делать если есть subObjects

        return readBytes;
    }
}
