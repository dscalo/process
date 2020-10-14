package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main {


    public static void main(String[] args) {
        RAM ram = new RAM(100);
        int quantum = 2;

        /* SETUP */
        ArrayList<Process> processes = new ArrayList<>();
        processes = createProcesses();
        final boolean[] shutdown = {false};

       // TODO: use the round robin to pluck a process from the array and add it to ram
        // TODO: somehow delete a process???
        boolean done = false;
        System.out.println("STARTINH ROUND ROBIN");

        while(!done) {
            for (int i = 0; i < processes.size(); i++) {
                // look at process
                Process p = processes.get(i);
                if ( p.getBurstTime() <= 0) {
                    done = true;
                    continue;
                }
                if (p.getRAM() == -1) {
                    System.out.println(("Adding process " + p.getId() + " to RAM"));
                    if(!ram.add(p)) {
                        System.out.println(("MEMORY FULL!! skipping process"));
                        continue;
                    }
                }

                // is it done?
                p.decrementBurstTime(quantum);
                System.out.println("Process " + p.getId() + " burst time is " + p.getBurstTime());
                if (p.getBurstTime() <= 0) {
                    System.out.println("Process " + p.getId() + " finished running");
                    done = true;

                    if (p.getRAM() != -1) {
                        System.out.println("Removing process " + p.getId() + " from RAM");
                        ram.deleteProcess(p.getId());
                    }
                } else {
                    done = false;
                    System.out.println(("Process " + p.getId() + " working!"));

                }
            }

        }

        // shutdown any processes still running


        System.out.println("EXITING, have a nice day!");
        System.exit(1);
    }

    public static ArrayList<Process> createProcesses() {
        ArrayList<Process> processes = new ArrayList<>();
        Random random = new Random();
        int numbOfProcesses = random.nextInt(10) + 1;

        for (int i = 0; i < numbOfProcesses; i++) {
            int size = random.nextInt(10) + 1;
            int burstTime = random.nextInt(20) + 4;
            Process p = new Process(size, burstTime);
            System.out.println("Creating process " + p.getId() + " with a size of " + size + " and a burst time of " + burstTime);
//            p.setThreads(random.nextInt(5) + 1);
//            p.start();
            processes.add(p);
        }

        return processes;

    }
}
