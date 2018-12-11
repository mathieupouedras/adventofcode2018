package domain;

import infrastructure.FileLineReader;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PolymerTest {

    @Test
    void should_contains_16_unit() {
        String representation = "dabAcCaCBAcCcaDA";

        Polymer polymer = new Polymer(representation);

        assertThat(polymer.size(), is(representation.length()));
    }

    @Test
    void should_return_the_correct_polymer_representation() {
        String representation = "dabAcCaCBAcCcaDA";

        Polymer polymer = new Polymer(representation);

        assertThat(polymer.toString(), is(representation));
    }

    @Test
    void should_have_units_to_remove() {
        String representation = "dabAcCaCBAcCcaDA";

        Polymer polymer = new Polymer(representation);

        int expectedIndexe = 4;

        assertThat(polymer.getIndexOfUnitToBeDestroyed(), is(expectedIndexe));
    }

    @Test
    void should_run_part1_with_sample_data() {
        String representation = "dabAcCaCBAcCcaDA";

        Polymer polymer = new Polymer(representation);

        Polymer newPolymer = polymer.execute();
        while(!polymer.equals(newPolymer)) {
            polymer = newPolymer;
            newPolymer = newPolymer.execute();
        }

        assertThat(newPolymer.toString(), is("dabCBAcaDA"));

    }

    @Test
    void should_run_part1_with_prod_data() {
        PolymerRepository polymerRepository = new PolymerRepository(new FileLineReader());

        Polymer polymer = polymerRepository.read("input.txt");

        Polymer newPolymer = polymer.execute();
        while(!polymer.equals(newPolymer)) {
            polymer = newPolymer;
            newPolymer = newPolymer.execute();
        }

        System.out.println(newPolymer.size());

    }

    @Test
    void should_run_part_2_with_prod_data() {
        PolymerRepository polymerRepository = new PolymerRepository(new FileLineReader());

        Polymer polymer = polymerRepository.read("input.txt");

        String representation = polymer.toString();

        Map<String, Integer> units = new HashMap<>();
        int i = 0;
        for (char c : polymer.toString().toCharArray()) {
            if (units.containsKey(String.valueOf(c).toLowerCase())) {
                continue;
            }
            String newRepresentation = representation.replace(String.valueOf(c).toLowerCase(), "");
            newRepresentation = newRepresentation.replace(String.valueOf(c).toUpperCase(), "");
            polymer = new Polymer(newRepresentation);
            Polymer newPolymer = polymer.execute();
            while(!polymer.equals(newPolymer)) {
                polymer = newPolymer;
                newPolymer = newPolymer.execute();
            }

            units.put(String.valueOf(c).toLowerCase(), newPolymer.size());

            i++;
            if (i%10 == 0) {
                System.out.println(i);
            }
        }

        Optional<Integer> min = units.values().stream().min(Comparator.naturalOrder());
        System.out.println(min.get());
    }


    @Test
    void should_run_part_2_with_sample_data() {
        String representation = "dabAcCaCBAcCcaDA";

        Polymer polymer = new Polymer(representation);

        Map<String, Integer> units = new HashMap<>();

        for (char c : polymer.toString().toCharArray()) {
            if (units.containsKey(String.valueOf(c).toLowerCase())) {
                continue;
            }
            String newRepresentation = representation.replace(String.valueOf(c).toLowerCase(), "");
            newRepresentation = newRepresentation.replace(String.valueOf(c).toUpperCase(), "");
            polymer = new Polymer(newRepresentation);
            Polymer newPolymer = polymer.execute();
            while(!polymer.equals(newPolymer)) {
                polymer = newPolymer;
                newPolymer = newPolymer.execute();
            }

            units.put(String.valueOf(c).toLowerCase(), newPolymer.size());
        }

        Optional<Integer> min = units.values().stream().min(Comparator.naturalOrder());
        System.out.println(min.get());
    }


}