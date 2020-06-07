package ru.fratask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fratask.builder.ObjectContentBuilder;
import ru.fratask.builder.ScanObjectBuilder;
import ru.fratask.entity.Report;
import ru.fratask.entity.ScanObject;
import ru.fratask.entity.ScanReport;
import ru.fratask.service.ScanEngine;

import java.util.Map;

@RestController
@RequestMapping("/scan")
public class ScanController {

    @Autowired
    private ScanEngine scanEngine;

    @PostMapping("/file")
    public ResponseEntity<Report> scan(@RequestBody Map<String, String> params) {
        if (!params.containsKey("path") && params.get("path") != null) {
            return ResponseEntity.status(500).build();
        }
        ScanObject scanObject = ScanObjectBuilder.build(ObjectContentBuilder.build(params.get("path")));
        if (scanObject == null) {
            return ResponseEntity.status(500).build();
        }
        Report report = scanEngine.scan(scanObject, "USER");
        return ResponseEntity.ok(report);
    }
}
