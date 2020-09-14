package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {


    public static void main(String[] args) {

        ArrayList<Process> processes = new ArrayList<>();

        processes.addAll(createProcesses());

        Random random = new Random();

        int numberToTerminate = random.nextInt(processes.size());

        System.out.println("Terminating " + numberToTerminate + " processes");

        for (int j = 0; j < numberToTerminate; j++) {
            int terminate = random.nextInt(processes.size());

            Process p = processes.get(terminate);
            System.out.println("Terminating process # " + p.getId());
            p.shutdown();
            processes.remove(terminate);
        }

        // shutdown any processes still running
        processes.forEach(( p) -> p.shutdown());
    }

    public static ArrayList<Process> createProcesses() {
        ArrayList<Process> processes = new ArrayList<>();
        Random random = new Random();
        int numbOfProcesses = random.nextInt(5) + 1;

        for (int i = 0; i < numbOfProcesses; i++) {
            Process p = new Process();
            System.out.println("Creating process " + p.getId());
            p.setThreads(random.nextInt(5) + 1);
            p.start();
            processes.add(p);
        }

        return processes;

    }
}
