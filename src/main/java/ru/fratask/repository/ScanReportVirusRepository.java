package ru.fratask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fratask.entity.ScanReportVirus;

@Repository
public interface ScanReportVirusRepository extends CrudRepository<ScanReportVirus, Integer> {
}
