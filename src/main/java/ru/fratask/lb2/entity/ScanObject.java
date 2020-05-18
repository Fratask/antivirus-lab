package ru.fratask.lb2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScanObject {

    private String name;
    private List<ScanRegion> regions;
    private List<ScanObject> subObjects;

    public int read(byte[] blocks, ScanObject parentObject) {
        int bytesRead = 0;
        if (parentObject != null) {
            System.out.println("Read child object start " + name);
        } else {
            System.out.println("Read object start " + name);
        }
        for (ScanRegion scanRegion : regions) {
            bytesRead += scanRegion.read(blocks);
        }
        if (subObjects != null) {
            for (ScanObject scanObject : subObjects) {
                bytesRead += scanObject.read(blocks, this);
            }
        }
        if (parentObject != null) {
            System.out.println("Read child object stop " + name);
        } else {
            System.out.println("Read object stop " + name);
        }
        return bytesRead;
    }

}
