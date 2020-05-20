package ru.fratask.useCase;

import ru.fratask.entity.Directory;
import ru.fratask.utils.DirectoryMonitor;

public class StopDirectoryMonitoringUseCase implements UseCase {

    private DirectoryMonitor directoryMonitor = DirectoryMonitor.getInstance();
    private Directory directory;

    @Override
    public void input() {
        directory = new Directory();
        directoryMonitor.monitorDirectory(directory);
        System.out.println("Directory monitoring started");
    }

    @Override
    public void output() {
        System.out.println("Directory removed from monitoring");
    }

    @Override
    public void run() {
        input();
        directoryMonitor.unmonitorDirectory(directory);
        output();
    }
}
