package ru.fratask.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Report {

    private String initiator;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int countOfScannedFiles;
    private int countOfViruses;
    private int countOfScannedRegions;
    private List<String> viruses;
}
