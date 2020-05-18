package ru.fratask.lb6.useCase;

import ru.fratask.lb5.entity.Directory;
import ru.fratask.lb5.monitor.DirectoryMonitor;

public class GetMonitoredDirectoriesUseCase implements UseCase {

    private DirectoryMonitor directoryMonitor = DirectoryMonitor.getInstance();
    private Directory directory;

    @Override
    public void input() {
        directory = new Directory();
        directoryMonitor.monitorDirectory(directory);
    }

    @Override
    public void output() {
        System.out.println(directoryMonitor.getActiveDirectories().toString());
    }

    @Override
    public void run() {
        input();
        output();
    }
}
