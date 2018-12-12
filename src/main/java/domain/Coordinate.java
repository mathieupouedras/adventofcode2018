package domain;

import java.util.Objects;

class Coordinate {

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private final int x;
    private final int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int manathanDistance(Coordinate otherCoordinate) {
        return Math.abs(this.x - otherCoordinate.x) + Math.abs(this.y - otherCoordinate.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
