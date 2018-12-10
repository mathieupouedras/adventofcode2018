package domain;

import infrastructure.FileLineReader;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ShiftTest {

    private static final int ANY_SHIFT_ID = 0;

    @Test
    void part1() {
        ActionRepository actionRepository = new ActionRepository("input.txt", new FileLineReader());
        List<Action> actions = actionRepository.readAll();
        actions.sort(Comparator.comparing(n -> n.getDate()));

        List<Nap> naps = new ArrayList<>();
        LocalDateTime start = null;
        Nap nap = null;

        int guardID = 0;
        for (Action action: actions) {
            if (action.isNewShift()) {
                guardID = action.getGuardID();
                if (start != null) {
                    nap.setEnd(action.getDate());
                    naps.add(nap);
                }
            }
            else if(action.isFallingASleep()) {
                nap = new Nap(guardID, action.getDate());
            }
            else {
                nap.setEnd(action.getDate());
                naps.add(nap);
                start = null;
            }
        }

        Map<Integer, Long> sleepers =
                naps.stream().collect(Collectors.toMap(n -> n.getGuardID(), n -> Duration.between(n.getStart(), n.getEnd()).toMinutes(), (oldval, newVal) -> oldval + newVal));


        int biggestSleeper = sleepers.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();

        Map<Integer, Integer> minutes = new HashMap<>();
        for (Nap bigNap : naps) {
            if (bigNap.getGuardID() == biggestSleeper) {
                for (int i = bigNap.getStart().getMinute(); i < bigNap.getEnd().getMinute(); i++) {
                    if (minutes.containsKey(i)) {
                        minutes.replace(i, minutes.get(i) + 1);
                    }
                    else {
                        minutes.put(i, 1);
                    }
                }
            }
            else  {
                continue;
            }
        }

        int minute = minutes.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        assertThat(biggestSleeper * minute, is(240));
    }

}