package domain;

import infrastructure.FileLineReader;

import java.util.ArrayList;
import java.util.List;

class ActionRepository {

    private final String fileName;

    private final FileLineReader fileLineReader;

    ActionRepository(String fileName, FileLineReader fileLineReader) {
        this.fileName = fileName;
        this.fileLineReader = fileLineReader;
    }

    List<Action> readAll() {
       List<Action> actions = new ArrayList<>();
        String[] lines = fileLineReader.read(fileName);
        for (String line : lines) {
            RecordParser recordParser = new RecordParser(line);
            Action action = recordParser.parseAction();
            if (action.isNewShift()) {
                action.setGuardID(recordParser.parseGuardID(recordParser.extractAction()));
            }
            actions.add(action);
        }

        return actions;
    }


}
