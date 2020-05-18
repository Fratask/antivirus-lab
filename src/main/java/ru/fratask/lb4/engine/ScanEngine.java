package ru.fratask.lb4.engine;


import ru.fratask.lb1.dao.InMemoryVirusDaoImpl;
import ru.fratask.lb1.dao.VirusDao;
import ru.fratask.lb1.entity.Virus;
import ru.fratask.lb2.entity.ScanObject;
import ru.fratask.lb2.entity.ScanRegion;
import ru.fratask.lb3.builder.ScanObjectBuilder;
import ru.fratask.lb4.report.ScanReport;
import ru.fratask.lb4.session.ScanSession;

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
            int counterOfBytes = 0;
            int counterOfObjects = 0;
            int counterOfFiles = 0;
            int counterOfViruses = 0;
            for (ScanRegion scanRegion : scanObject.getRegions()) {
                while ((counterOfBytes += scanRegion.read(data)) != -1) {
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
            System.out.println(scanReport.toString());
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

}
