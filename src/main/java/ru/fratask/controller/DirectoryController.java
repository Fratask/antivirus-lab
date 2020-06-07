package ru.fratask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fratask.entity.Directory;
import ru.fratask.service.DirectoryMonitor;

import java.util.Map;

@RestController
@RequestMapping("/directory")
public class DirectoryController {

    @Autowired
    private DirectoryMonitor directoryMonitor;

    @GetMapping
    public ResponseEntity<Map<String, Directory>> getAllDirectories() {
        return ResponseEntity.ok(directoryMonitor.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addDirectoryToMonitoring(@RequestBody Map<String, String> params) {
        directoryMonitor.addDirectory(params.get("path"));
    }

    @DeleteMapping
    public void removeDirectoryFromMonitoring(@RequestBody Map<String, String> params) {
        directoryMonitor.removeDirectory(params.get("path"));
    }


}
