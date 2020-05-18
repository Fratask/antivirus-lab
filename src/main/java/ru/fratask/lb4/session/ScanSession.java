package ru.fratask.lb4.session;


import java.util.Queue;

public class ScanSession {

    private boolean busy;
    private static volatile ScanSession instance;

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public static ScanSession getInstance() {
        if (instance == null) {
            synchronized (ScanSession.class) {
                if (instance == null) {
                    instance = new ScanSession();
                }
            }
        }
        return instance;
    }

}
