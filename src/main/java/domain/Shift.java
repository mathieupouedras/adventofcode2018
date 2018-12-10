package domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

class Shift {

    private final List<Action> actions;

    Shift(List<Action> actions) {
        this.actions = actions;
    }


    public int getSleepDuration() {
        LocalDateTime startSleepingDate = null;
        int totalSleepDuration = 0;
        for (Action action : actions) {
            if (action.isFallingASleep()) {
                startSleepingDate = action.getDate();
            }
            else if (wasSleeping(startSleepingDate, action)) {
                totalSleepDuration += Duration.between(startSleepingDate, action.getDate()).toSeconds();
                startSleepingDate = null;
            }
        }

        return totalSleepDuration;
    }

    private boolean wasSleeping(LocalDateTime startSleepingDate, Action action) {
        return startSleepingDate != null && (action.isWakingUp() || action.isNewShift());
    }
}
