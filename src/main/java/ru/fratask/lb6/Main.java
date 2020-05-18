package ru.fratask.lb6;

import ru.fratask.lb6.useCase.*;

public class Main {

    public static void main(String[] args) {
        new StartScanUseCase().run();
        new StopScanUseCase().run();
        new PauseScanUseCase().run();
        new AddDirectoryToMonitorUseCase().run();
        new RemoveDirectoryFromMonitorUseCase().run();
        new GetMonitoredDirectoriesUseCase().run();
        new StartDirectoryMonitoringUseCase().run();
        new StopDirectoryMonitoringUseCase().run();
    }
}
