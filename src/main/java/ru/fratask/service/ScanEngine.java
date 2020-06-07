package ru.fratask.service;

import com.google.common.primitives.Bytes;
import org.springframework.stereotype.Service;
import ru.fratask.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScanEngine {

    private final VirusService virusService;

    public ScanEngine(VirusService virusService) {
        this.virusService = virusService;
    }

    public Report scan(ScanObject scanObject, String initiator) {
        byte[][] buffer = new byte[scanObject.getScanRegions().size()][ScanRegion.getStandardRegionSize()];
        Report report = new Report();
        report.setStartTime(LocalDateTime.now());
        report.setInitiator(initiator);

        scanObject.readBlock(buffer, null);
        List<Virus> viruses = checkDataForViruses(buffer);

        report.setEndTime(LocalDateTime.now());
        report.setCountOfScannedFiles(1);
        report.setCountOfViruses(viruses.size());
        report.setViruses(viruses.stream().map(Virus::getName).collect(Collectors.toList()));
        report.setCountOfScannedRegions(scanObject.getScanRegions().size());
        return report;
    }

    private List<Virus> checkDataForViruses(byte[][] data) {
        List<Virus> virusList = virusService.getAllViruses();
        List<Virus> result = new ArrayList<>();

        for (Virus virus : virusList) {
            int minIndex = virus.getMinOffset() / ScanRegion.getStandardRegionSize();
            int maxIndex = ((virus.getMaxOffset() + virus.getLength()) / ScanRegion.getStandardRegionSize()) + 1;
            maxIndex = Math.min(maxIndex, data.length);
            for (int i = minIndex; i < maxIndex; i ++) {
                if (bytesContainsSequence(data[i], virus.getSequence())) {
                    result.add(virus);
                }
            }
        }
        return result;
    }

    private boolean bytesContainsSequence(byte[] data, byte[] sequence) {
        return Bytes.indexOf(data, sequence) != -1;
    }
}
