package domain;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.of;

class RecordParser {

    public String extractDate() {
        int start = record.indexOf(SEPARATOR.START_DATE_TIME.getSeparatorType()) + 1;
        int end = record.indexOf(SEPARATOR.END_DATE_TIME.getSeparatorType());
        return record.substring(start, end);
    }

    public String extractAction() {
        int start = record.indexOf(SEPARATOR.START_ACTION.getSeparatorType()) + 2;
        int end = record.length();
        return record.substring(start, end);
    }

    public int parseGuardID(String action) {
        String[] actionParts = action.split(SEPARATOR.ACTION.getSeparatorType());
        String guardID = actionParts[1].substring(1);
        return Integer.valueOf(guardID);
    }

    public Action parseAction() {
        LocalDateTime date = parseDateTime(extractDate());
        Action action = null;
        if (record.contains(ACTION_TYPE.NEW_SHIFT.indentifier)) {
            action = new Action(date, true, false, false);
        }

        if(record.contains(ACTION_TYPE.FALLING_ASLEEP.indentifier)) {
            action = new Action(date, false, true, false);
        }

        if (record.contains(ACTION_TYPE.WAKING_UP.indentifier)) {
            action = new Action(date, false, false, true);
        }
        return action;
    }

    enum ACTION_TYPE {
        NEW_SHIFT("#"),
        FALLING_ASLEEP("falls asleep"),
        WAKING_UP("wakes up");

        private final String indentifier;

        ACTION_TYPE(String indentifier) {
            this.indentifier = indentifier;
        }
    }

    enum SEPARATOR {
        ACTION(" "),
        START_ACTION("]"),
        START_DATE_TIME("["),
        END_DATE_TIME("]"),
        DATE_TIME(" "),
        DATE("-"),
        TIME(":");

        public String getSeparatorType() {
            return separatorType;
        }

        private final String separatorType;

        SEPARATOR(String separatorType) {
            this.separatorType = separatorType;
        }
    }

    private final String record;

    RecordParser(String record) {
        this.record = record;
    }

    LocalDateTime parseDateTime(String dateTime) {
        String date = dateTime.split(SEPARATOR.DATE_TIME.getSeparatorType())[0];
        String dateParts[] = date.split(SEPARATOR.DATE.getSeparatorType());
        int year = Integer.valueOf(dateParts[0]);
        int month = Integer.valueOf(dateParts[1]);
        int day = Integer.valueOf(dateParts[2]);

        String time = dateTime.split(SEPARATOR.DATE_TIME.getSeparatorType())[1];
        String[] timeParts = time.split(SEPARATOR.TIME.getSeparatorType());
        int hour = Integer.valueOf(timeParts[0]);
        int minute = Integer.valueOf(timeParts[1]);

        return of(year, month, day, hour, minute);
    }
}
