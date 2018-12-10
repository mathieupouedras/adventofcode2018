package domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.of;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;

class ParserTest {

    private static final String ANY_RECORD = null;

    @Test
    void should_parse_date() {
        String dateText = "1518-11-01 00:00";
        LocalDateTime expectedLocalDateTime = of(1518, 11, 1, 0, 0);
        RecordParser parser = new RecordParser(ANY_RECORD);

        assertThat(parser.parseDateTime(dateText), is(expectedLocalDateTime));
    }

    @Test
    void should_extract_date() {
        String record = "[1518-11-01 00:00] Guard #10 begins shift";
        String expectedDate = "1518-11-01 00:00";

        RecordParser parser = new RecordParser(record);

        assertThat(parser.extractDate(), is(expectedDate));
    }

    @Test
    void should_extract_action() {
        String record = "[1518-11-01 00:00] Guard #10 begins shift";
        String expectedAction = "Guard #10 begins shift";

        RecordParser parser = new RecordParser(record);
        assertThat(parser.extractAction(), is(expectedAction));
    }

    @Test

    void should_parse_guardID() {
        String action = "Guard #10 begins shift";
        int expectedGuardID = 10;

        RecordParser recordParser = new RecordParser(ANY_RECORD);
        assertThat(recordParser.parseGuardID(action), is(10));
    }


    @Test
    void should_create_new_shift_action() {
        String record = "[1518-11-01 00:00] Guard #10 begins shift";
        RecordParser recordParser = new RecordParser(record);

        Action action = recordParser.parseAction();

        assertThat(action.isNewShift(), is(true));

    }

    @Test
    void should_create_falling_asleep_action() {
        String record = "[1518-11-01 00:05] falls asleep";
        RecordParser recordParser = new RecordParser(record);

        Action action = recordParser.parseAction();

        assertThat(action.isFallingASleep(), is(true));

    }

    @Test
    void should_create_falling_waking_up_action() {
        String record = "[1518-11-01 00:25] wakes up";
        RecordParser recordParser = new RecordParser(record);

        Action action = recordParser.parseAction();

        assertThat(action.isWakingUp(), is(true));

    }
}
