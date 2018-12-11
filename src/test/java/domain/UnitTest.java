package domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UnitTest {

    @Test
    void should_be_same_type() {
        Unit unit = new Unit('A');
        Unit otherUnit = new Unit('A');

        assertThat(unit.isSameType(otherUnit), is(true));
    }

    @Test
    void should_be_same_type_with_same_letter_and_different_case() {
        Unit unit = new Unit('A');
        Unit otherUnit = new Unit('a');

        assertThat(unit.isSameType(otherUnit), is(true));
    }

    @Test
    void should_be_same_type_with_different_letter() {
        Unit unit = new Unit('A');
        Unit otherUnit = new Unit('b');

        assertThat(unit.isSameType(otherUnit), is(false));
    }

    @Test
    void should_be_opposite_polarity() {
        Unit unit = new Unit('A');
        Unit otherUnit = new Unit('a');

        assertThat(unit.isOppositePolarity(otherUnit), is(true));
    }

    @Test
    void should_not_be_opposite_polarity_for_2_different_letters() {
        Unit unit = new Unit('A');
        Unit otherUnnit = new Unit('B');

        assertThat(unit.isOppositePolarity(otherUnnit), is(false));
    }

    @Test
    void should_not_be_opposite_polarity_for_2_identical_letters_same_case() {
        Unit unit = new Unit('A');
        Unit otherUnnit = new Unit('A');

        assertThat(unit.isOppositePolarity(otherUnnit), is(false));
    }


}