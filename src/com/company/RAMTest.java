package com.company;

import static org.junit.jupiter.api.Assertions.*;

class RAMTest {

    @org.junit.jupiter.api.Test
    void add() {
        RAM ram = new RAM(5);
        Process p = new Process(3);
        ram.add(p);

        long[] memory = ram.getRAM();
        assert (memory[0] == p.getId());
        assert (memory[1] == p.getId());
        assert (memory[2] == p.getId());
        assert (memory[3] == -1);

    }

    @org.junit.jupiter.api.Test
    void deleteProcess() {
        RAM ram = new RAM(5);
        Process p = new Process(3);
        ram.add(p);
        ram.deleteProcess(p.getId());

        long[] memory = ram.getRAM();
        assert (memory[0] == -1);
        assert (memory[1] == -1);
        assert (memory[2] == -1);
    }

    @org.junit.jupiter.api.Test
    void fillingUpMemory() {
        RAM ram = new RAM(10);
        Process p1 = new Process(3);
        Process p2 = new Process(7);
        ram.add(p1);
        ram.add(p2);

        long[] memory = ram.getRAM();

        assert (memory[0] == p1.getId());
        assert (memory[9] == p2.getId());

    }

    @org.junit.jupiter.api.Test
    void addingToMiddle() {
        RAM ram = new RAM(10);
        Process p1 = new Process(4);
        Process p2 = new Process(3);
        Process p3 = new Process(3);

        ram.add(p1);
        ram.add(p2);

        ram.deleteProcess(p1.getId());

        ram.add(p3);

        long[] memory = ram.getRAM();

        for (int i = 0; i< memory.length; i++) {
            System.out.println(i + ": " + memory[i]);
        }
        assert (memory[0] == p3.getId());
        assert (memory[1] == p3.getId());
        assert (memory[2] == p3.getId());
        assert (memory[3] == -1);
        assert (memory[4] == p2.getId());
        assert (memory[5] == p2.getId());
        assert (memory[6] == p2.getId());

    }
}