package domain;

import java.util.*;

import static domain.Status.TO_BE_DONE;

class Step {

    private int duration;

    public void setStartedSecond(int startedSecond) {
        this.startedSecond = startedSecond;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getStartedSecond() {
        return startedSecond;
    }

    private int startedSecond = 0;

    public Status getStatus() {
        return status;
    }

    private Status status;

    public int getDuration() {
        return duration;
    }

    int duration() {
        int count = 1;
        for(char c = 'A'; c < 'Z'; c++) {
            if (this.name.equalsIgnoreCase(String.valueOf(c))) {
                break;
            }
            count++;
        }
        return count;
    }

    public String getName() {
        return name;
    }

    private final String name;

    public List<Step> getParents() {
        return parents;
    }

    public Set<Step> getChildren() {
        return children;
    }

    public void addParent(Step parent) {
        this.parents.add(parent);
    }

    private List<Step> parents;
    private final Set children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return name.equals(step.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Step(String name) {
        this.name = name;
        this.children = new HashSet();
        this.parents = new ArrayList<>();
        this.duration = duration();
        this.status = TO_BE_DONE;
    }

    public void addChild(Step child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return name;
    }

}
