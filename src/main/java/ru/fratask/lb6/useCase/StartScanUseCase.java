package ru.fratask.lb6.useCase;

import ru.fratask.lb2.entity.ScanObject;
import ru.fratask.lb4.engine.ScanEngine;

public class StartScanUseCase implements UseCase {

    private ScanObject scanObject;

    @Override
    public void input() {
        scanObject = ScanObject.builder()
                .name("object")
                .build();
        System.out.println("Created scan object");
    }

    @Override
    public void output() {
        System.out.println("Object scanned");
    }

    @Override
    public void run() {
        input();
        ScanEngine.getInstance().playScan();
        ScanEngine.getInstance().scan(scanObject);
        output();
    }
}
