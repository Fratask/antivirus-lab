package ru.fratask.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.fratask.config.Configuration;
import ru.fratask.model.Directory;
import ru.fratask.model.Report;
import ru.fratask.model.State;
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
    private final Configuration configuration;
    private List<Directory> oldDirectories;

    public MonitoringScheduler(ScanEngine scanEngine, DirectoryMonitorService directoryMonitorService, ReportRepository reportRepository, Configuration configuration) {
        this.scanEngine = scanEngine;
        this.directoryMonitorService = directoryMonitorService;
        this.reportRepository = reportRepository;
        oldDirectories = new ArrayList<>(directoryMonitorService.getAll().values());
        this.configuration = configuration;
        scanDirectories();
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void scanDirectories() {
        if (configuration.getState().equals(State.ENABLE)) {
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
    }

    @Scheduled(cron = "* * * * * *")
    public void monitor() {
        if (configuration.getState().equals(State.ENABLE)) {
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

}
