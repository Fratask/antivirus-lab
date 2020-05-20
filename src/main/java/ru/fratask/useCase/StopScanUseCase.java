package ru.fratask.useCase;

import ru.fratask.entity.ScanObject;
import ru.fratask.utils.ScanEngine;

public class StopScanUseCase implements UseCase {

    ScanObject scanObject;

    @Override
    public void input() {
        scanObject = ScanObject.builder()
                .name("object")
                .build();
        ScanEngine.getInstance().scan(scanObject);
        System.out.println("Started scan object");
    }

    @Override
    public void output() {
        System.out.println("Object scan stopped");
    }

    @Override
    public void run() {
        input();
        ScanEngine.getInstance().stopScan();
        output();
    }
}
