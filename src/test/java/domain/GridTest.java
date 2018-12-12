package domain;

import infrastructure.FileLineReader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class GridTest {

    private final String EXEMPLE_GRID =
            "..........\n" +
            ".A........\n" +
            "..........\n" +
            "........C.\n" +
            "...D......\n" +
            ".....E....\n" +
            ".B........\n" +
            "..........\n" +
            "..........\n" +
            "........F.";

    @Test
    void should_draw_grid() {
        Coordinate coordinateA = new Coordinate(1, 1);
        Coordinate coordinateB = new Coordinate(1, 6);
        Coordinate coordinateC = new Coordinate(8, 3);
        Coordinate coordinateD = new Coordinate(3, 4);
        Coordinate coordinateE = new Coordinate(5, 5);
        Coordinate coordinateF = new Coordinate(8, 9);

        Place placeA = new Place("A", coordinateA);
        Place placeB = new Place("B", coordinateB);
        Place placeC = new Place("C", coordinateC);
        Place placeD = new Place("D", coordinateD);
        Place placeE = new Place("E", coordinateE);
        Place placeF = new Place("F", coordinateF);

        List<Place> places = new ArrayList();
        places.add(placeA);
        places.add(placeB);
        places.add(placeC);
        places.add(placeD);
        places.add(placeE);
        places.add(placeF);

        Grid grid = new Grid(places);

        assertThat(grid.size(), is(10));

        assertThat(grid.toString(), is(EXEMPLE_GRID));

        //grid.computeManathanDistance();

        //assertThat(grid.getLocations()[1][3].getName(), is("a"));

        //assertThat(grid.getLargestNonInfinitArea(), is(17));

        GridRepository gridRepository = new GridRepository(new FileLineReader(), "input.txt");
        grid = gridRepository.read();
        grid.computeManathanDistance();

        assertThat(grid.getLargestNonInfinitArea(), is(4829));

        grid.part2();



    }
}

