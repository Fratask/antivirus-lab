package ru.fratask.lb6.useCase;

import ru.fratask.lb5.entity.Directory;
import ru.fratask.lb5.monitor.DirectoryMonitor;

public class StartDirectoryMonitoringUseCase implements UseCase {

    private DirectoryMonitor directoryMonitor = DirectoryMonitor.getInstance();
    private Directory directory;

    @Override
    public void input() {
        System.out.println("Creating directory to monitoring");
        directory = new Directory();
    }

    @Override
    public void output() {
        System.out.println("Monitoring started");
    }

    @Override
    public void run() {
        input();
        System.out.println("Start monitoring");
        directoryMonitor.monitorDirectory(directory);
        output();
    }
}
