package ru.fratask.service;

import com.google.common.primitives.Bytes;
import org.springframework.stereotype.Service;
import ru.fratask.config.Configuration;
import ru.fratask.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScanEngine {

    private final VirusService virusService;
    private final Configuration configuration;

    public ScanEngine(VirusService virusService, Configuration configuration) {
        this.virusService = virusService;
        this.configuration = configuration;
    }

    public Report scan(ScanObject scanObject, String initiator) {
        byte[][] buffer = new byte[scanObject.getScanRegions().size()][ScanRegion.getStandardRegionSize()];
        Report report = new Report();
        report.setInitiator(initiator);

        scanObject.readBlock(buffer, null);
        List<Virus> viruses = checkDataForViruses(buffer);

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
            byte[][] dataForAnalyze = new byte[maxIndex-minIndex+1][ScanRegion.getStandardRegionSize()];
            for (int i = minIndex; i < maxIndex; i++) {
                dataForAnalyze[i-minIndex] = data[i];
            }
            if (bytesContainsSequence(dataForAnalyze, virus.getSequence())) {
                result.add(virus);
            }
        }
        return result;
    }

    private boolean bytesContainsSequence(byte[][] data, byte[] sequence) {
        int length = 0;
        for (byte[] datum : data) {
            length += datum.length;
        }
        Byte[] dataForAnalyze = new Byte[length];
        for (byte[] datum : data) {
            Byte[] regionByte = parseToByteClassArray(datum);
            contactBytes(dataForAnalyze, regionByte);
        }
        return Bytes.indexOf(parseToBytePrimitiveArray(dataForAnalyze), sequence) != -1;
    }

    public Report scanDirectory(Directory directory, String initiator) {
        Report result = new Report();
        result.setInitiator(initiator);
        result.setStartTime(LocalDateTime.now());
        result.setViruses(new ArrayList<>());
        for (ScanObject scanObject : directory.getScanObjects()) {
            Report report = scan(scanObject, initiator);
            result.setCountOfScannedRegions(result.getCountOfScannedRegions() + report.getCountOfScannedRegions());
            result.setCountOfScannedFiles(result.getCountOfScannedFiles() + report.getCountOfScannedFiles());
            result.setCountOfViruses(result.getCountOfViruses() + report.getCountOfViruses());
            result.getViruses().addAll(report.getViruses());
        }
        result.setEndTime(LocalDateTime.now());
        return result;
    }

    public boolean switchState(State state) {
        if (!configuration.getState().equals(state)) {
            configuration.setState(state);
        }
        return true;
    }

    private Byte[] parseToByteClassArray(byte[] data) {
        Byte[] result = new Byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        return result;
    }

    private byte[] parseToBytePrimitiveArray(Byte[] data) {
        byte[] result = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        return result;
    }


    private static void contactBytes(Byte[] a, Byte[] b) {
        int i = 0;
        while (i < a.length) {
            if (a[i] == null) {
                for (Byte aByte : b) {
                    a[i] = aByte;
                    i++;
                }
                return;
            }
            i++;
        }
    }

}
