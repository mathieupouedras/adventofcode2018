package logistic;

import java.util.List;

public class Warehouse {

    private final List<Box> boxes;

    public Warehouse(List<Box> boxes) {
        this.boxes = boxes;
    }

    public int checksum() {
        int count2letters = 0;
        int count3letters = 0;
        for (Box box: boxes) {
            if (box.hasIdContainingExactlySameLetter(2)) {
                count2letters ++;
            }
            if (box.hasIdContainingExactlySameLetter(3)) {
                count3letters ++;
            }
        }
        return count2letters * count3letters;
    }
}
