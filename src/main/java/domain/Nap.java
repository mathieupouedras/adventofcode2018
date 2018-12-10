package domain;

import java.time.LocalDateTime;

public class Nap {

    public int getGuardID() {
        return guardID;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    private final int guardID;

    public Nap(int guardID, LocalDateTime start) {
        this.guardID = guardID;
        this.start = start;
    }

    private final LocalDateTime start;

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    private LocalDateTime end;

}
