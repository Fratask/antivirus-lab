package ru.fratask.lb4.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.fratask.lb1.entity.Virus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScanReport {

    private String initiator;
    private LocalDateTime startScan;
    private LocalDateTime endScan;
    private int countOfScannedFiles;
    private int countOfScannedObjects;
    private int countOfVirusFound;
    private List<Virus> viruses;

}