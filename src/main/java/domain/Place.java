package domain;

import java.util.Objects;

class Place {

    public String getName() {
        return name;
    }

    private final String name;

    Coordinate getCoordinate() {
        return coordinate;
    }

    private final Coordinate coordinate;

    Place(String name, Coordinate coordinate) {
        this.name = name;
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return coordinate.equals(place.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
