package day_09;

import java.util.ArrayList;
import java.util.List;

public class DiskFragmenter {

    public static long calculateChecksum(String input) {
        long checksum = 0;

        List<Integer> files = getFiles(input);
        List<Integer> fileSystem = buildFileSystem(files);

        int left = 0;
        int right = fileSystem.size() - 1;

        while (left < right) {
            if (fileSystem.get(left) == -1) {
                while (right > left && fileSystem.get(right) == -1) {
                    right--;
                }

                if (right <= left) {
                    break;
                }

                fileSystem.set(left, fileSystem.get(right));
                fileSystem.set(right, -1);
            }

            left++;
        }

        for (int i = 0; i < fileSystem.size(); i++) {
            if (fileSystem.get(i) == -1) {
                break;
            }

            checksum += fileSystem.get(i) * i;
        }

        return checksum;
    }

    public static long calculateBlockChecksum(String input) {
        long checksum = 0;

        List<Integer> files = getFiles(input);
        List<Integer> fileSystem = buildFileSystem(files);
        int right = fileSystem.size() - 1;

        while (right >= 0) {
            if (fileSystem.get(right) != -1) {
                boolean found = false;
                int moveBlockSize = 0;
                int blockId = fileSystem.get(right);

                while (right >= 0 && fileSystem.get(right) == blockId) {
                    moveBlockSize++;
                    right--;
                }

                int tempLeft = 0;
                int tempRight;

                while (tempLeft < right) {
                    if (fileSystem.get(tempLeft) == -1) {
                        tempRight = tempLeft;
                        while (tempRight < fileSystem.size() && fileSystem.get(tempRight) == -1
                            && (tempRight - tempLeft) < moveBlockSize) {
                            tempRight++;
                        }

                        if (tempRight - tempLeft >= moveBlockSize) {
                            found = true;
                            break;
                        }
                        else {
                            tempLeft = tempRight;
                        }
                    }

                    tempLeft++;
                }

                if (found) {
                    for (int i = 0; i < moveBlockSize; i++) {
                        fileSystem.set(tempLeft + i, fileSystem.get(right + i + 1));
                        fileSystem.set(right + i + 1, -1);
                    }
                }
            }
            else {
                right--;
            }
        }

        for (int i = 0; i < fileSystem.size(); i++) {
            if (fileSystem.get(i) == -1) {
                continue;
            }

            checksum += fileSystem.get(i) * i;
        }

        return checksum;
    }

    private static List<Integer> getFiles(String input) {
        List<Integer> files = new ArrayList<>();

        for (char ch : input.toCharArray()) {
            files.add(Character.getNumericValue(ch));
        }

        return files;
    }

    private static List<Integer> buildFileSystem(List<Integer> files) {
        List<Integer> fileSystem = new ArrayList<>();
        boolean isBlock = true;

        for (int i = 0; i < files.size(); i++) {
            if (isBlock) {
                int blockSize = files.get(i);

                for (int j = 0; j < blockSize; j++) {
                    fileSystem.add(i / 2);
                }

                isBlock = false;
            }
            else {
                int freeSpace = files.get(i);
                for (int j = 0; j < freeSpace; j++) {
                    fileSystem.add(-1);
                }

                isBlock = true;
            }
        }

        return fileSystem;
    }
}
