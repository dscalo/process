package com.company;

import java.util.ArrayList;

public class Process extends Thread {
   private ArrayList<String> instructions;
   private StringBuilder state;
   private int threads;
   private boolean terminate = false;
   private int size = 0;
   private int burstTime;
   private int RAM = -1;


   public Process() {
       this.threads = 1;
   }

    public Process(int size) {
        this.size = size;
        this.threads = 1;
        this.burstTime = 2;


    }

   public Process(int size, int burstTime ) {
       this.size = size;
       this.threads = 1;
       this.burstTime = burstTime;
   }

   public void setThreads(int amount) {
       this.threads = amount;
   }

   public void shutdown() {
       System.out.println("Process " + this.getId() + " shutting down");
       this.terminate = true;
   }

   public int getSize() {return this.size;}
   public int getBurstTime() {return this.burstTime;}
   public int getRAM() {return this.RAM;}
   public void setRam(int ram) {this.RAM = ram;}
   public void decrementBurstTime(int d) {
       this.burstTime -= d;
        if (this.burstTime < 0) {
            this.burstTime = 0;
        }
   }


    @Override
    public void run() {
        while(!this.terminate) {
            for (int i = 0; i < this.threads; i++) {
                ProcessThread pt = new ProcessThread(i, this.getId());
                pt.start();
            }
        }
    }
}
