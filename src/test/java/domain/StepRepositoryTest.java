package domain;

import infrastructure.FileLineReader;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Status.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class StepRepositoryTest {

    private static final int FIX_DURATION = 60;

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

    @Test
    void part2() {
        StepRepository stepRepository = new StepRepository("input.txt", new FileLineReader());
        List<Step> steps = new ArrayList<>();
        steps = stepRepository.readAll();
        List<Step> originalSteps = steps;
        int time = 0;
        int workers = 5;

        while(true) {
            boolean allDone = true;
            for (Step step: steps) {
                if (!step.getStatus().equals(FINISHED)) {
                    allDone = false;
                }
            }
            if (allDone) {
                break;
            }
            for (Step step: steps) {
                if (step.getStatus().equals(IN_PROGRESS)) {
                    if (time - step.getStartedSecond() == FIX_DURATION + step.getDuration()) {
                        step.setStatus(FINISHED);
                        steps.set(steps.indexOf(step), step);
                        workers++;
                    }
                }
            }
            int busyWorkers = 0;
            for (int i = 0; i < workers; i++) {
                Step nextStep = nextStep(steps);
                if (nextStep == null) {
                    break;
                }
                nextStep.setStatus(IN_PROGRESS);
                nextStep.setStartedSecond(time);
                steps.set(steps.indexOf(nextStep), nextStep);
                busyWorkers++;
            }
            workers = workers - busyWorkers;
            System.out.println(time);
            time++;
        }

        time = time -1;
        assertThat(time, is(15));
    }

    private Step nextStep(List<Step> steps) {
        List<Step> nextSteps = new ArrayList<>();
        for (Step step: steps) {
            if (step.getParents().isEmpty() || areParentsDone(step, steps)) {
                if (step.getStatus().equals(TO_BE_DONE)) {
                    nextSteps.add(step);
                }
            }
        }
        nextSteps = nextSteps.stream().sorted((s1, s2) -> s1.getName().compareTo(s2.getName())).collect(Collectors.toList());
        if(nextSteps.isEmpty()) {
            return null;
        }
        return nextSteps.get(0);
    }

    private boolean areParentsDone(Step step, List<Step> steps) {
        boolean parentsDone = true;
        for(Step parent : step.getParents()) {
            for (Step s : steps) {
                if (s.equals(parent)) {
                    if (!s.getStatus().equals(FINISHED)) {
                        parentsDone = false;
                    }
                }
            }
        }

        return parentsDone;
    }

}