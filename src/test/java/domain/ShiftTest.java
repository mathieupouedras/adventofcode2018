package domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

public class ShiftTest {

    @Test
    void should_calculate_time_asleep() {
        Action action1 = new RecordParser("[1518-11-01 00:00] Guard #10 begins shift").parseAction();
        Action action2 = new RecordParser("[1518-11-01 00:05] falls asleep").parseAction();
        Action action3 = new RecordParser("[1518-11-01 00:25] wakes up").parseAction();
        Action action4 = new RecordParser("[1518-11-01 00:30] falls asleep").parseAction();
        Action action5 = new RecordParser("[1518-11-01 00:55] wakes up").parseAction();
        Action action6 = new RecordParser("[1518-11-01 23:58] Guard #99 begins shift").parseAction();

        List<Action> actions = new ArrayList();
        actions.add(action1);
        actions.add(action2);
        actions.add(action3);
        actions.add(action4);
        actions.add(action5);
        actions.add(action6);

        Shift shift = new Shift(actions);

        assertThat(shift.getSleepDuration(), is(45));
    }

}