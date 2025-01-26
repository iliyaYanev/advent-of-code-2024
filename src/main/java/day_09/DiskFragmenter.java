package day_09;

import util.Disk;

public class DiskFragmenter {

    public static long fileSystemChecksum(String input, boolean files) {
        Disk disk = new Disk(input);

        if (files) {
            disk.compactFiles();
        }
        else {
            disk.compactBlocks();
        }

        return disk.checksum();
    }
}
