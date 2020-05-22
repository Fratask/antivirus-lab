package ru.fratask.utils;


import ru.fratask.dao.InMemoryVirusDaoImpl;
import ru.fratask.dao.VirusDao;
import ru.fratask.entity.Virus;
import ru.fratask.entity.ScanObject;
import ru.fratask.entity.ScanRegion;
import ru.fratask.entity.ScanReport;
import ru.fratask.entity.ScanSession;
import ru.fratask.gui.Window;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class ScanEngine {

    private static volatile ScanEngine instance;
    private Queue<ScanObject> sessions = new ArrayDeque<>(6);
    private VirusDao virusDao = InMemoryVirusDaoImpl.getInstance();
    private boolean stop;
    private boolean pause;
    private final ScanReport scanReport = new ScanReport();

    public void scan(ScanObject scanObject) {
        if (stop) {
            Thread.currentThread().interrupt();
        }
        ScanSession scanSession = ScanSession.getInstance();
        if (scanSession.isBusy()) {
            if (sessions.offer(scanObject)) {
                System.out.println("Element added to queue");
            } else {
                System.out.println("Element doesn't added to queue");
            }
            return;
        }
        synchronized (scanReport) {
            scanSession.setBusy(true);
            System.out.println("Start scan " + scanObject.getName());
            scanReport.setStartScan(LocalDateTime.now());
            scanReport.setInitiator("User");
            byte[] data = new byte[1024];
            int counterOfObjects = 0;
            int counterOfFiles = 0;
            int counterOfViruses = 0;
            for (ScanRegion scanRegion : scanObject.getRegions()) {
                while (scanRegion.read(data) != -1) { // ошибка, прибавлять не нужно
                    if (checkDataForViruses(data) != null) {
                        counterOfViruses++;
                    }
                }
            }
            scanReport.setCountOfScannedFiles(++counterOfFiles);
            scanReport.setCountOfScannedObjects(++counterOfObjects);
            scanReport.setCountOfVirusFound(counterOfViruses);
            scanReport.setEndScan(LocalDateTime.now());

            System.out.println("end scan " + scanObject.getName());
            scanSession.setBusy(false);
            new Thread(this::checkQueue).start();
            printStats(scanReport);
        }
    }

    public void playScan() {
        stop = false;
        pause = false;
        notify();
    }

    public void stopScan() {
        stop = true;
    }

    public void pauseScan() {
        pause = true;
    }

    public static ScanEngine getInstance() {
        if (instance == null) {
            synchronized (ScanEngine.class) {
                if (instance == null) {
                    instance = new ScanEngine();
                }
            }
        }
        return instance;
    }

    private void checkQueue() {
        if (!sessions.isEmpty()) {
            scan(sessions.peek());
        }
    }

    private Virus checkDataForViruses(byte[] data) {

        for (Virus virus : virusDao.findAll()) {
            if (Arrays.equals(virus.getSignature().getData(), data)) {
                return virus;
            }
        }
        return null;
    }

    private void printStats(ScanReport scanReport) {
        Window window = Window.getInstance();
        window.getReportLabel().setText("Scan report " + scanReport.getStartScan() + " - " + scanReport.getEndScan());
        window.getFilesCountLabel().setText("Count of files: " + scanReport.getCountOfScannedFiles());
        window.getObjectsCountLabel().setText("Count of files: " + scanReport.getCountOfScannedObjects());
        window.getVirusesCountLabel().setText("Count of viruses: " + scanReport.getCountOfVirusFound());
        window.getVirusNameLabel().setText("Viruses names: " + scanReport.getViruses());
    }

}
