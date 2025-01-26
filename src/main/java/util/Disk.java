package util;

import java.util.ArrayList;
import java.util.List;

public class Disk {

    private static final int EMPTY = -1;

    private final int[] blocks;

    public Disk(String map) {
        int id = 0;
        List<Integer> block = new ArrayList<>();

        for (int i = 0; i < map.length(); i++) {
            int length = map.charAt(i) - '0';

            if (i % 2 == 0) {
                for (int j = 0; j < length; j++) {
                    block.add(id);
                }

                id++;
            } else {
                for (int j = 0; j < length; j++) {
                    block.add(-1);
                }
            }
        }

        this.blocks = new int[block.size()];

        for (int i = 0; i < block.size(); i++) {
            this.blocks[i] = block.get(i);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public void compactBlocks() {
        int empty = 0;
        int block = blocks.length - 1;

        while (empty < block) {
            for (; block >= 0 && blocks[block] == EMPTY; block--);
            for (; empty < block && blocks[empty] != EMPTY; empty++);

            if (empty < block) {
                blocks[empty] = blocks[block];
                blocks[block] = EMPTY;
                block--;
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    public void compactFiles() {
        int emptyFirst = 0;
        int emptyLast = 0;
        int fileFirst = blocks.length - 1;
        int fileLast = blocks.length - 1;

        while (emptyLast < fileFirst) {
            for (; fileLast >= 0 && blocks[fileLast] == EMPTY; fileLast--);
            for (fileFirst = fileLast; fileFirst >= 0 && blocks[fileFirst] == blocks[fileLast]; fileFirst--);

            fileFirst++;

            do {
                for (; emptyFirst < fileFirst && blocks[emptyFirst] != EMPTY; emptyFirst++)
                    ;
                for (emptyLast = emptyFirst; emptyLast < fileFirst && blocks[emptyLast] == EMPTY;
                    emptyLast++)
                    ;
                emptyLast--;
                if (emptyLast < fileFirst && emptyLast - emptyFirst >= fileLast - fileFirst) {
                    for (int i = 0; i <= fileLast - fileFirst; i++) {
                        blocks[emptyFirst + i] = blocks[fileFirst + i];
                        blocks[fileFirst + i] = EMPTY;
                    }
                    break;
                }
                emptyFirst = emptyLast + 1;
            } while (emptyFirst < fileFirst);

            fileLast = fileFirst - 1;
            emptyFirst = emptyLast = 0;
        }
    }

    public long checksum() {
        long checksum = 0L;

        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] != EMPTY)
                checksum += (long) i * blocks[i];
        }

        return checksum;
    }
}
