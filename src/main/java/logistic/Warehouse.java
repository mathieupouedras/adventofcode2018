package logistic;

import java.util.ArrayList;
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

    public String findCorrectLetters() {
        List<Box> oneLetterDiffBoxes = new ArrayList<>();
        for (Box box: boxes) {
            for (Box box2: boxes) {
                if (!oneLetterDiffBoxes.isEmpty()) {
                    break;
                }
                if (!box.getId().equals(box2.getId())) {
                   if (box.isAlmostIdentical(box2)) {
                       oneLetterDiffBoxes.add(box);
                       oneLetterDiffBoxes.add(box2);
                       break;
                   }
                }
            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < oneLetterDiffBoxes.get(0).getId().length(); i ++) {
            if (oneLetterDiffBoxes.get(0).getId().toCharArray()[i]
                    == oneLetterDiffBoxes.get(1).getId().toCharArray()[i]) {
                stringBuilder.append(oneLetterDiffBoxes.get(0).getId().toCharArray()[i]);
            }
        }

        return stringBuilder.toString();
    }
}
