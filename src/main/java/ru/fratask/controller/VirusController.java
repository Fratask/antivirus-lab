package ru.fratask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fratask.model.Virus;
import ru.fratask.service.VirusService;

import java.util.List;

@RestController
@RequestMapping("/virus")
public class VirusController {

    private final VirusService virusService;

    public VirusController(VirusService virusService) {
        this.virusService = virusService;
    }

    @PostMapping
    public void saveVirus(@RequestBody Virus virus) {
        virusService.save(virus);
    }

    @GetMapping
    public ResponseEntity<List<Virus>> getAll() {
        return ResponseEntity.ok(virusService.getAllViruses());
    }

}
