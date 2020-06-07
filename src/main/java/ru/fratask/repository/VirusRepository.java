package ru.fratask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fratask.entity.Virus;

import java.util.List;

@Repository
public interface VirusRepository extends CrudRepository<Virus, Long> {

    List<Virus> findAll();
}
