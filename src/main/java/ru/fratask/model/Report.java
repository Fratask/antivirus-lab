package ru.fratask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Report {

    private String initiator;
    private int countOfScannedFiles;
    private int countOfViruses;
    private int countOfScannedRegions;
    @JsonIgnore
    private LocalDateTime startTime;
    @JsonIgnore
    private LocalDateTime endTime;
    @JsonIgnore
    private List<String> viruses;
}
