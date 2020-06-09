package ru.fratask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fratask.builder.ObjectContentBuilder;
import ru.fratask.builder.ScanObjectBuilder;
import ru.fratask.model.Report;
import ru.fratask.model.ScanObject;
import ru.fratask.model.State;
import ru.fratask.service.ScanEngine;

import java.util.Map;

@RestController
@RequestMapping("/scan")
public class ScanController {


    private final ScanEngine scanEngine;

    public ScanController(ScanEngine scanEngine) {
        this.scanEngine = scanEngine;
    }

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

    @GetMapping("/enable")
    public void enableScanSystem() {
        scanEngine.switchState(State.ENABLE);

    }

    @GetMapping("/disable")
    public void disableScanSystem() {
        scanEngine.switchState(State.DISABLE);
    }
}
