package domain;

class PolymerRepository {

    private final LineReader lineReader;

    PolymerRepository(LineReader lineReader) {
        this.lineReader = lineReader;
    }

    Polymer read(String fileName) {
        String[] lines = this.lineReader.read(fileName);
        if (lines.length > 1) {
            throw new RuntimeException("file should contain a single line");
        }

        return new Polymer(lines[0]);
    }
}
