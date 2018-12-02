package logistic;

import domain.BoxRepository;
import infrastructure.BoxFileRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class WarehouseTest {

    @Test
    public void should_produces_checksum_of_12() {

        Box box1 = new Box("abcdef");
        Box box2 = new Box("bababc");
        Box box3 = new Box("abbcde");
        Box box4 = new Box("abcccd");
        Box box5 = new Box("aabcdd");
        Box box6 = new Box("abcdee");
        Box box7 = new Box("ababab");

        List<Box> boxes = new ArrayList<Box>();
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);
        boxes.add(box4);
        boxes.add(box5);
        boxes.add(box6);
        boxes.add(box7);

        Warehouse warehouse = new Warehouse(boxes);

        int checksum = warehouse.checksum();

        assertThat(checksum, is(12));

    }

    @Test
    public void part1() {
        BoxRepository boxRepository = new BoxFileRepository();
        Box[] boxes = boxRepository.readAll();

        Warehouse warehouse = new Warehouse(Arrays.asList(boxes));

        System.out.println(warehouse.checksum());
    }

    @Test
    public void should_find_correct_boxes() {

        Box box1 = new Box("abcde");
        Box box2 = new Box("fghij");
        Box box3 = new Box("klmno");
        Box box4 = new Box("pqrst");
        Box box5 = new Box("fguij");
        Box box6 = new Box("axcye");
        Box box7 = new Box("wvxyz");

        List<Box> boxes = new ArrayList<Box>();
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);
        boxes.add(box4);
        boxes.add(box5);
        boxes.add(box6);
        boxes.add(box7);


        Warehouse warehouse = new Warehouse(boxes);

        String correctLetters = warehouse.findCorrectLetters();

        assertThat(correctLetters, is("fgij"));

    }

    @Test
    public void part2() {
        BoxRepository boxRepository = new BoxFileRepository();
        Box[] boxes = boxRepository.readAll();

        Warehouse warehouse = new Warehouse(Arrays.asList(boxes));

        System.out.println(warehouse.findCorrectLetters());

    }


    }
