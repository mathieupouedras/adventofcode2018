package domain;

import java.util.*;

class Step {

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
    }

    public void addChild(Step child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return name;
    }

}
