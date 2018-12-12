package domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CoordinateTest {

    @Test
    void should_calculate_manathan_distance_5() {
        Coordinate coordinate = new Coordinate(1, 1);
        Coordinate coordinate5 = new Coordinate(5, 2);
        int expectedManathanDistance = 5;

        int manathanDistance = coordinate.manathanDistance(coordinate5);

        assertThat(manathanDistance, is(expectedManathanDistance));
    }
}