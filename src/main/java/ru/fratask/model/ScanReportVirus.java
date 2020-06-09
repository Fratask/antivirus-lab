package ru.fratask.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ScanReportVirus {

    @Id
    private int id;
    private int scanReportId;
    private int virusId;
}
