package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {

        /* SETUP */
        Random random = new Random();
        ArrayList<Process> processes = new ArrayList<>();
        final boolean[] shutdown = {false};

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Stopping Loop");
                shutdown[0] = true;
            }
        };

        Timer timer = new Timer("KillProcesses");

        timer.schedule(task, 3000);

        /* PROCESS LOOP */
        while(!shutdown[0]) {
            processes.addAll(createProcesses());
            int numberToTerminate = random.nextInt(processes.size());

            System.out.println("Terminating " + numberToTerminate + " processes");

            for (int j = 0; j < numberToTerminate; j++) {
                int terminate = random.nextInt(processes.size());

                Process p = processes.get(terminate);
                System.out.println("Terminating process # " + p.getId());
                p.shutdown();
                processes.remove(terminate);
            }
        }

        /* CLEANUP */
        System.out.println("SHUTTING DOWN PROGRAM!!!!");

        // shutdown any processes still running
        processes.forEach(( p) -> p.shutdown());

        System.out.println("EXITING, have a nice day!");
        System.exit(1);
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
