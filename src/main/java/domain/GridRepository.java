package domain;

import java.util.ArrayList;
import java.util.List;

class GridRepository {
    private final LineReader lineReader;
    private final String fileName;

    GridRepository(LineReader lineReader, String fileName) {
        this.lineReader = lineReader;
        this.fileName = fileName;
    }
    
    Grid read() {
        String[] lines = lineReader.read(fileName);
        List<Place> places = new ArrayList();

        for (int i = 0; i < lines.length; i++) {
            int x = Integer.valueOf(lines[i].split(",")[0]);
            int y = Integer.valueOf(lines[i].split(",")[1].trim());

            Coordinate coordinate = new Coordinate(x, y);
            String name = String.valueOf(i);
            Place place = new Place(name, coordinate);
            places.add(place);
        }

        return new Grid(places);

    }
}
