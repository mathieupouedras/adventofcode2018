package domain;

import java.util.*;
import java.util.stream.Collectors;

class StepRepository {

    private final String fileName;
    private final LineReader lineReader;
    private final List<Step> steps;
    private List<Step> elus;

    StepRepository(String fileName, LineReader lineReader) {
        this.fileName = fileName;
        this.lineReader = lineReader;
        steps = new ArrayList();
        elus = new ArrayList<>();
    }

    List<Step> readAll() {
        String[] lines = lineReader.read(fileName);
        for (String line : lines) {
            String[] tokens = line.split(" ");
            String parentName = tokens[1];
            String childName = tokens[7];
            Step parent = findByName(parentName);
            Step child = findByName(childName);
            if (parent == null) {
                parent = new Step(parentName);
            }
            if (child == null) {
                child = new Step(childName);
            }

            parent.addChild(child);
            child.addParent(parent);
            steps.removeIf(s -> s.getName().equals(parentName));
            steps.add(parent);
            steps.removeIf(s -> s.getName().equals(childName));
            steps.add(child);

        }

        return steps;

    }

    Step findByName(String name) {
        for (Step step : steps) {
            if (step.getName().equals(name)) {
                return step;
            }
        }

        return null;
    }

    List<Step> getRoot() {
        List<Step> result = new ArrayList();
        for (Step step: steps) {
            if (step.getParents().size() == 0) {
                result.add(step);
            }
        }
        result = result.stream().sorted((s1, s2) -> s1.getName().compareTo(s2.getName())).collect(Collectors.toList());
        return result;
    }

    String findStepOrder(List<Step> available, StringBuilder stringBuilder) {
        if (available.isEmpty()) {
            return stringBuilder.toString();
        }
        Step elu = available.get(0);
        stringBuilder.append(elu.getName());
        elus.add(elu);
        available = getAvailaibleSteps(available, elu);
        return findStepOrder(available, stringBuilder);
    }

    List<Step> getAvailaibleSteps(List<Step> available, Step elu) {
        available.remove(elu);
        for (Step s : elu.getChildren()) {
            if (elus.containsAll(s.getParents())) {
                available.add(s);
            }
        }

        available = available.stream().sorted((s1, s2) -> s1.getName().compareTo(s2.getName())).collect(Collectors.toList());
        return available;
    }
}
