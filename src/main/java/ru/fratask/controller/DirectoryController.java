package ru.fratask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fratask.model.Directory;
import ru.fratask.service.DirectoryMonitorService;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/directory")
public class DirectoryController {

    @Autowired
    private DirectoryMonitorService directoryMonitorService;

    @GetMapping
    public ResponseEntity getAllDirectories() {
        Collection<Directory> result = directoryMonitorService.getAll().values();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDirectoryToMonitoring(@RequestBody Map<String, String> params) {
        directoryMonitorService.addDirectory(params.get("path"));
    }

    @PostMapping("/remove")
    public void removeDirectoryFromMonitoring(@RequestBody Map<String, String> params) {
        directoryMonitorService.removeDirectory(params.get("path"));
    }


}
