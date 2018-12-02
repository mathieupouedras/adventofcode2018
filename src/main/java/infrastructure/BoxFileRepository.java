package infrastructure;

import domain.BoxRepository;
import domain.LineReader;
import logistic.Box;

public class BoxFileRepository implements BoxRepository {

    private final LineReader lineReader;

    public BoxFileRepository() {
        lineReader = new FileLineReader();
    }

    @Override
    public Box[] readAll() {
        String[] lines = lineReader.read("input.txt");
        Box[] boxes = new Box[lines.length];

        for (int i = 0; i < lines.length; i++) {
            Box box = new Box(lines[i]);
            boxes[i] = box;
        }

        return boxes;
    }
}
