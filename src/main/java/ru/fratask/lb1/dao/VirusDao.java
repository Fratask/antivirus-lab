package ru.fratask.lb1.dao;

import ru.fratask.lb1.entity.Virus;

import java.util.List;
import java.util.Optional;

public interface VirusDao {

    /**
     * Create a virus and save it in database
     * @param virus created by user
     * @return created Virus object
     */
    Virus create(Virus virus);

    /**
     * Get optional object of Virus by id
     * @param id of virus
     * @return optional object of Virus object
     */
    Optional<Virus> findVirusById(Long id);

    /**
     * Get optional object of Virus by name
     * @param name of virus
     * @return optional object of Virus object
     */
    Optional<Virus> findVirusByName(String name);

    /**
     * Find all viruses from database
     * @return List of Virus
     */
    List<Virus> findAll();

    /**
     * Updating virus in database. Gets id from virus and update by id
     * @param virus - updating virus
     * @return updated virus
     */
    Virus updateVirusById(Virus virus);

    /**
     * Updating virus in database. Gets name from virus and update by name
     * @param virus - updating virus
     * @return updated virus
     */
    Virus updateVirusByName(Virus virus);

    /**
     * Delete virus by name
     * @param name of virus
     */
    void deleteVirusByName(String name);
}
