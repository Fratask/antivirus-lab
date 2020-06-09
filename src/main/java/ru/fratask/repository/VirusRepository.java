package ru.fratask.repository;

import org.springframework.stereotype.Component;
import ru.fratask.model.Virus;

import java.util.ArrayList;
import java.util.List;

@Component
public class VirusRepository {

    private List<Virus> viruses = new ArrayList<>();

    public VirusRepository() {
        createTestViruses();
    }

    public Virus save(Virus virus) {
        viruses.add(virus);
        return virus;
    }

    public List<Virus> findAll() {
        return viruses;
    }

    private void createTestViruses() {
        for (int i = 0; i < 10; i++) {
            Virus virus = new Virus();
            virus.setName("TestVirus" + i);
            virus.setLength(1);
            virus.setMinOffset(256);
            virus.setMaxOffset(4096);
            virus.setSequence(new byte[]{Integer.valueOf(i).byteValue()});
            viruses.add(virus);
        }
    }
}
