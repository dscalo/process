package com.company;

import java.util.ArrayList;

public class Process extends Thread {
   private ArrayList<String> instructions;
   private StringBuilder state;
   private int threads;
   private boolean terminate = false;


   public Process() {
       this.threads = 1;
   }

   public void setThreads(int amount) {
       this.threads = amount;
   }

   public void shutdown() {
       System.out.println("Process " + this.getId() + " shutting down");
       this.terminate = true;
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