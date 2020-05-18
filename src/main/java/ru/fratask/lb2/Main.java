package ru.fratask.lb2;

import ru.fratask.lb2.entity.FileObjectContentImpl;
import ru.fratask.lb2.entity.ObjectContent;
import ru.fratask.lb2.entity.ScanObject;
import ru.fratask.lb2.entity.ScanRegion;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<ScanRegion> scanRegions = new LinkedList<>();
        ObjectContent content = new FileObjectContentImpl("C:\\Users\\Amir\\Desktop\\file.exe");
        scanRegions.add(ScanRegion.builder()
                .objectContent(content)
                .offset(0)
                .length(8)
                .build());
        scanRegions.add(ScanRegion.builder()
                .objectContent(content)
                .offset(0)
                .length(8)
                .build());
        scanRegions.add(ScanRegion.builder()
                .objectContent(content)
                .offset(0)
                .length(8)
                .build());

        ScanObject object = ScanObject.builder()
                .subObjects(null)
                .regions(scanRegions)
                .name("name")
                .build();

        byte[] data = new byte[32];
        System.out.println(object.read(data, null));

        for (byte datum : data) {
            System.out.println(datum);
        }

    }

}
