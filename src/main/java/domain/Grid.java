package domain;

import java.util.List;

class Grid {
    public Place[][] getLocations() {
        return locations;
    }

    private final List<Place> places;
    private Place[][] locations;
    private int size;

    Grid(List<Place> places) {
        this.places = places;
        initGrid();
    }

    void initGrid() {
        this.size = Math.max(getMaxX(), getMaxY()) + 1;
        locations = new Place[size][size];
        createEmptyGrid();
        for (Place place: places) {
            locations[place.getCoordinate().getX()][place.getCoordinate().getY()]
                    = new Place(place.getName(), place.getCoordinate());
        }
    }

    private void createEmptyGrid() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                locations[x][y] = new Place(".", new Coordinate(x, y));
            }
        }
    }

    private int getMaxX() {
        return places.stream().mapToInt(p -> p.getCoordinate().getX()).max().orElse(0);
    }
    private int getMaxY() {
        return places.stream().mapToInt(p -> p.getCoordinate().getY()).max().orElse(0);
    }

    private int getMinX() {
        return places.stream().mapToInt(p -> p.getCoordinate().getX()).min().orElse(0);
    }
    private int getMinY() {
        return places.stream().mapToInt(p -> p.getCoordinate().getY()).min().orElse(0);
    }


    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
             stringBuilder.append(locations[x][y].getName());
            }
            if (y < size - 1) {
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    void part2() {
        int result = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int distance = 0;
                for (Place place : places) {
                    distance += locations[x][y].getCoordinate().manathanDistance(place.getCoordinate());
                }
                if (distance <10000) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    void computeManathanDistance() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int minDistance = Integer.MAX_VALUE;
                for (Place place : places) {
                    if (isPlace(locations[x][y])) {
                        continue;
                    }
                    int distance = locations[x][y].getCoordinate().manathanDistance(place.getCoordinate());
                    if (distance < minDistance) {
                        locations[x][y] = new Place(place.getName().toLowerCase(), new Coordinate(x, y));
                        minDistance = distance;
                    }
                    else if (distance == minDistance) {
                        locations[x][y] = new Place(".", new Coordinate(x, y));
                    }
                }
            }
        }
    }

    private boolean isPlace(Place location) {
        for (Place place : places) {
            if (place.equals(location)) {
                return true;
            }
        }
        return false;
    }

    public int getLargestNonInfinitArea() {
        int maxAreaSize = 0;
        for (Place place : places) {
            int areaSize = 0;
            if (isInfiniteArea(place)) {
                System.out.println(place.getName());
                continue;
            }
            else {
                for (int y = 0; y < size; y++) {
                    for (int x = 0; x < size; x++) {
                        if (place.getName().equalsIgnoreCase(locations[x][y].getName())) {
                            areaSize++;
                        }
                    }
                }
            }
            if (areaSize > maxAreaSize && areaSize < 5000) {
                maxAreaSize = areaSize;
            }
        }

        return maxAreaSize;
    }

    private boolean isInfiniteArea(Place place) {
        if (place.getCoordinate().getX() == getMaxX() || place.getCoordinate().getX() == getMinX()
            || place.getCoordinate().getY() == getMaxY() || place.getCoordinate().getY() == getMinY()) {
            return true;
        }

        return false;
    }
}
