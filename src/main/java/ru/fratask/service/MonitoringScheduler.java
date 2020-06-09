package ru.fratask.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.fratask.entity.Directory;
import ru.fratask.entity.Report;
import ru.fratask.repository.ReportRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MonitoringScheduler {

    private final ScanEngine scanEngine;
    private final DirectoryMonitorService directoryMonitorService;
    private final ReportRepository reportRepository;
    private List<Directory> oldDirectories;

    public MonitoringScheduler(ScanEngine scanEngine, DirectoryMonitorService directoryMonitorService, ReportRepository reportRepository) {
        this.scanEngine = scanEngine;
        this.directoryMonitorService = directoryMonitorService;
        this.reportRepository = reportRepository;
        oldDirectories = new ArrayList<>(directoryMonitorService.getAll().values());
        scanDirectories();
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void scanDirectories() {
        Collection<Directory> directories = directoryMonitorService.getAll().values();
        Report result = new Report();
        result.setInitiator("System monitoring");
        result.setStartTime(LocalDateTime.now());
        result.setViruses(new ArrayList<>());
        for (Directory directory : directories) {
            Report report = scanEngine.scanDirectory(directory, "System monitoring");
            result.setCountOfScannedRegions(result.getCountOfScannedRegions() + report.getCountOfScannedRegions());
            result.setCountOfScannedFiles(result.getCountOfScannedFiles() + report.getCountOfScannedFiles());
            result.setCountOfViruses(result.getCountOfViruses() + report.getCountOfViruses());
            result.getViruses().addAll(report.getViruses());
        }
        result.setEndTime(LocalDateTime.now());
        reportRepository.save(result);
    }

    @Scheduled(cron = "* * * * * *")
    public void monitor() {
        List<Directory> directories = new ArrayList<>(directoryMonitorService.getAll().values());
        if (directories.size() != oldDirectories.size()) {
            Report result = new Report();
            result.setInitiator("System monitoring");
            result.setStartTime(LocalDateTime.now());
            result.setViruses(new ArrayList<>());
            for (Directory directory : directories) {
                Report report = scanEngine.scanDirectory(directory, "System monitoring");
                result.setCountOfScannedRegions(result.getCountOfScannedRegions() + report.getCountOfScannedRegions());
                result.setCountOfScannedFiles(result.getCountOfScannedFiles() + report.getCountOfScannedFiles());
                result.setCountOfViruses(result.getCountOfViruses() + report.getCountOfViruses());
                result.getViruses().addAll(report.getViruses());
            }
            result.setEndTime(LocalDateTime.now());
            reportRepository.save(result);
            return;
        }

        for (int i = 0; i < directories.size(); i++) {
            if (oldDirectories.get(i).getCountOfFiles() != directories.get(i).getCountOfFiles()) {
                Report result = new Report();
                result.setInitiator("System monitoring");
                result.setStartTime(LocalDateTime.now());
                result.setViruses(new ArrayList<>());
                for (Directory directory : directories) {
                    Report report = scanEngine.scanDirectory(directory, "System monitoring");
                    result.setCountOfScannedRegions(result.getCountOfScannedRegions() + report.getCountOfScannedRegions());
                    result.setCountOfScannedFiles(result.getCountOfScannedFiles() + report.getCountOfScannedFiles());
                    result.setCountOfViruses(result.getCountOfViruses() + report.getCountOfViruses());
                    result.getViruses().addAll(report.getViruses());
                }
                result.setEndTime(LocalDateTime.now());
                reportRepository.save(result);
            }
        }
    }

}
