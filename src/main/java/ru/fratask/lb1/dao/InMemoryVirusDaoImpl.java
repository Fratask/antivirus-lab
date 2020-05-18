package ru.fratask.lb1.dao;

import ru.fratask.lb1.entity.Virus;
import ru.fratask.lb1.exception.AntivirusException;
import ru.fratask.lb1.exception.AntivirusResponseCode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryVirusDaoImpl implements VirusDao {

    /**
     * Implementation of virus storage in memory
     */
    List<Virus> virusRepository = new LinkedList<>();
    private Long index = 0L;
    private static volatile InMemoryVirusDaoImpl instance;

    public static InMemoryVirusDaoImpl getInstance() {
        if (instance == null) {
            synchronized (InMemoryVirusDaoImpl.class) {
                if (instance == null) {
                    instance = new InMemoryVirusDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Virus create(Virus virus) {
        if (virusRepository.stream().anyMatch(virus1 -> virus.getName().equals(virus1.getName()))) {
            throw new AntivirusException(AntivirusResponseCode.DAO_CREATE_ERROR);
        }
        virus.setId(++index);
        virusRepository.add(virus);
        virusRepository.sort(Comparator.comparing(Virus::getId));
        return virus;
    }

    @Override
    public Optional<Virus> findVirusById(Long id) {
        int first = 0;
        int last = virusRepository.size()-1;
        int mid = (first+last)/2;
        while (first <= last) {
            if (virusRepository.get(mid).getId() < id) {
                first = mid + 1;
            } else if (virusRepository.get(mid).getId().equals(id)) {
                return Optional.of(virusRepository.get(mid));
            } else {
                last = mid -1;
            }
            mid = (first+last)/2;
        }

        return Optional.empty();
    }

    @Override
    public Optional<Virus> findVirusByName(String name) {
        for (Virus virus : virusRepository) {
            if (virus.getName().equals(name)) {
                return Optional.of(virus);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Virus> findAll() {
        return virusRepository;
    }

    @Override
    public Virus updateVirusById(Virus virus) {
        if (virusRepository.stream().noneMatch(virus1 -> virus.getId().equals(virus1.getId()))) {
            throw new AntivirusException(AntivirusResponseCode.DAO_UPDATE_ERROR);
        }
        virusRepository.remove(virusRepository.stream().filter(virus1 -> virus.getId().equals(virus1.getId())).collect(Collectors.toList()).get(0));
        virusRepository.add(virus);
        return virus;
    }

    @Override
    public Virus updateVirusByName(Virus virus) {
        if (virusRepository.stream().noneMatch(virus1 -> virus.getName().equals(virus1.getName()))) {
            throw new AntivirusException(AntivirusResponseCode.DAO_UPDATE_ERROR);
        }
        virusRepository.remove(virusRepository.stream().filter(virus1 -> virus.getName().equals(virus1.getName())).collect(Collectors.toList()).get(0));
        virusRepository.add(virus);
        return virus;
    }

    @Override
    public void deleteVirusByName(String name) {
        if (virusRepository.stream().noneMatch(virus1 -> virus1.getName().equals(name))) {
            throw new AntivirusException(AntivirusResponseCode.DAO_DELETE_ERROR);
        }
        virusRepository.remove(virusRepository.stream().filter(virus1 -> virus1.getName().equals(name)).collect(Collectors.toList()).get(0));
    }
}
