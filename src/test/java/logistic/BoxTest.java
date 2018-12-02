package logistic;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BoxTest {

    @Test
    public void should_not_contains_exactly_two_of_any_letter() {
        String id = "abcdef";
        Box box = new Box(id);

        assertThat(box.hasIdContainingExactlySameLetter(2), is(false));
    }

    @Test
    public void should_contains_exactly_two_of_any_letter() {
        String id = "bababc";
        Box box = new Box(id);

        assertThat(box.hasIdContainingExactlySameLetter(2), is(true));
    }

    @Test
    public void should_contains_exactly_three_of_any_letter() {
        String id = "bababc";
        Box box = new Box(id);

        assertThat(box.hasIdContainingExactlySameLetter(3), is(true));
    }


}