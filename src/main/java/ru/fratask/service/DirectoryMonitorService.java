package ru.fratask.service;

import org.springframework.stereotype.Service;
import ru.fratask.entity.Directory;

import java.util.HashMap;
import java.util.Map;

@Service
public class DirectoryMonitorService {

    private Map<String, Directory> directories = new HashMap<>();

    public DirectoryMonitorService() {
        addStandardDirectories();
    }

    public boolean addDirectory(String path) {
        if (directories.containsKey(path)) {
            return false;
        }
        directories.put(path, Directory.build(path));
        return true;
    }

    public boolean removeDirectory(String path) {
        if (!directories.containsKey(path)) {
            return false;
        }
        directories.remove(path);
        return true;
    }

    public boolean isMonitoring(String path) {
        return directories.containsKey(path);
    }

    public Directory getDirectory(String path) {
        if (!directories.containsKey(path)) {
            return null;
        }
        return directories.get(path);
    }

    public Map<String, Directory> getAll() {
        return directories;
    }

    private void addStandardDirectories() {
        String userName = System.getProperty("user.name");
        addDirectory("C:/Users/" + userName + "/Downloads");
    }


}
