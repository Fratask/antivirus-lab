package ru.fratask.lb5.monitor;

import ru.fratask.lb5.entity.Directory;

import java.util.LinkedList;
import java.util.List;

public class DirectoryMonitor {

    List<Directory> activeDirectories = new LinkedList<>();
    private static volatile DirectoryMonitor instance;

    public static DirectoryMonitor getInstance() {
        if (instance == null) {
            synchronized (DirectoryMonitor.class) {
                if (instance == null) {
                    instance = new DirectoryMonitor();
                }
            }
        }
        return instance;
    }


    public void monitorDirectory(Directory directory) {
        activeDirectories.add(directory);
    }

    public void unmonitorDirectory(Directory directory) {
        activeDirectories.remove(directory);
    }

    public boolean isMonitoring(Directory directory) {
        return activeDirectories.contains(directory);
    }

    public List<Directory> getActiveDirectories() {
        return activeDirectories;
    }

}
