package ru.fratask.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fratask.entity.ScanReport;

@Repository
public interface ScanReportRepository extends CrudRepository<ScanReport, Integer> {
}
