package logistic;

public class Box {

    private final String id;

    public Box(String id) {
        this.id = id;
    }

    boolean hasIdContainingExactlySameLetter(int letterCount) {
        for (char letter: id.toCharArray()) {
            int count = 0;
            for (char letter2: id.toCharArray()) {
                if (letter == letter2) {
                    count ++;
                }
            }
            if (count == letterCount) {
                return true;
            }
        }

        return false;
    }

}
