package ru.fratask.service;

import org.springframework.stereotype.Service;
import ru.fratask.entity.Virus;
import ru.fratask.repository.VirusRepository;

import java.util.List;

@Service
public class VirusService {

    private final VirusRepository virusRepository;

    public VirusService(VirusRepository virusRepository) {
        this.virusRepository = virusRepository;
    }

    public Virus save(Virus virus){
        return virusRepository.save(virus);
    }

    public List<Virus> getAllViruses() {
        return virusRepository.findAll();
    }
}
