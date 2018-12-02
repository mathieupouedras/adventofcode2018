package logistic;

public class Box {

    private final String id;

    public String getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Box{" +
                "id='" + id + '\'' +
                '}';
    }

    public boolean isAlmostIdentical(Box other) {
        int identicalLetters = 0;
        for (int i = 0; i < this.id.length(); i ++) {
            if (this.getId().toCharArray()[i] == other.getId().toCharArray()[i]) {
                identicalLetters ++;
            }
        }

        return identicalLetters == this.id.length() -1;


    }
}
