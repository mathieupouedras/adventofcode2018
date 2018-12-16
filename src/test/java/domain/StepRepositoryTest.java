package domain;

import infrastructure.FileLineReader;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class StepRepositoryTest {

    @Test
    void should_have_no_parent() {
        StepRepository stepRepository = new StepRepository("input.txt", new FileLineReader());
        List<Step> steps = new ArrayList<>();
        steps = stepRepository.readAll();

        assertThat(steps.size(), is(6));

        assertThat(stepRepository.findByName("C").getParents().size(), is(1));

        assertThat(stepRepository.findByName("C").getChildren().size(), is(2));

        assertThat(stepRepository.findByName("E").getChildren().size(), is(0));
    }

    @Test
    void should_return_root() {
        StepRepository stepRepository = new StepRepository("input.txt", new FileLineReader());
        List<Step> steps = new ArrayList<>();
        steps = stepRepository.readAll();

        Step expectedRoot = new Step("C");

        assertThat(stepRepository.getRoot().get(0), is(expectedRoot));

    }

    @Test
    void part1_with_sample_data() {
        StepRepository stepRepository = new StepRepository("input_short.txt", new FileLineReader());
        List<Step> steps = new ArrayList<>();
        steps = stepRepository.readAll();

        List<Step> available = new ArrayList();
        available.addAll(stepRepository.getRoot());
        String stepOrder = stepRepository.findStepOrder(available, new StringBuilder());

        assertThat(stepOrder, is("CABDFE"));
    }

}