package domain;

import java.time.LocalDateTime;

class Action {
    private final LocalDateTime date;
    private final boolean isNewShift;
    private final boolean isFallingASleep;

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isNewShift() {
        return isNewShift;
    }

    public boolean isFallingASleep() {
        return isFallingASleep;
    }

    public boolean isWakingUp() {
        return isWakingUp;
    }

    private final boolean isWakingUp;



    protected Action(LocalDateTime date, boolean isNewShift, boolean isFallingASleep, boolean isWakingUp) {
        this.date = date;
        this.isNewShift = isNewShift;
        this.isFallingASleep = isFallingASleep;
        this.isWakingUp = isWakingUp;
    }
}
