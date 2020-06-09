package ru.fratask.repository;

import org.springframework.stereotype.Component;
import ru.fratask.model.Report;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportRepository {

    private List<Report> reports = new ArrayList<>();

    public Report save(Report report) {
        reports.add(report);
        return report;
    }

    public List<Report> findAll() {
        return reports;
    }

}
