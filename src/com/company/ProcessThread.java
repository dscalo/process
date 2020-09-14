package com.company;

import java.util.ArrayList;

public class ProcessThread extends Thread {
    private int id;
    private long processId;

    private StringBuilder state;
    private ArrayList<String> instructions;

    public ProcessThread(int id, long processId) {
        super();
        this.id = id;
        this.processId = processId;

    }
    public void run() {
        System.out.println("Running thread: " + this.id + " on process: " + this.processId);
    }
}
