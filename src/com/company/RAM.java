package com.company;

public class RAM {

    long[] blocks;

    public RAM(int size) {
        blocks = new long[size];

        for (int i = 0; i < size; i++) {
            blocks[i] = -1;
        }
    }

    public boolean add(Process p)  {
        int size = p.getSize();
        int window = 0;
        int i;

        for(i = 0; i < this.blocks.length; i++) {
            if (this.blocks[i] == -1) {
                window++;
            } else {
                window = 0;
            }

            if (window == size) {
                break;
            }
        }

        if (window == 0) {
            // cannot fit object;
            return false;
        }

        // store the process id
        int offset = i - (size - 1);
        for(int j = 0; j < size; j++) {
            blocks[j + offset] = p.getId();
        }
        p.setRam(offset);
        return true;

    }

    public long[] getRAM () {return blocks;}

    public void deleteProcess(long id) {
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] == id) {
                blocks[i] = -1;
            }
        }
    }
}
