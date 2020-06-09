package ru.fratask.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Report {

    private String initiator;
    private int countOfScannedFiles;
    private int countOfViruses;
    private int countOfScannedRegions;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> viruses;
}
