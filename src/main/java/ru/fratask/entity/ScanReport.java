package ru.fratask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScanReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String initiator;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int countOfScannedFiles;
    private int countOfViruses;

}
