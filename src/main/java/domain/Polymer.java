package domain;

import java.util.*;

class Polymer {

    private final Unit[] units;

    Polymer(String representation) {
        units = new Unit[representation.length()];
        char[] chars = representation.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            units[i] = new Unit(chars[i]);
        }
    }

    Polymer(Unit[] units) {
        this.units = units;
    }

    int size() {
        return this.units.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Unit unit : units) {
            stringBuilder.append(unit.toString());
        }
        return stringBuilder.toString();
    }

    int getIndexOfUnitToBeDestroyed() {
        int unitIndex = 0;
        int firstIndexToRemove;
        while (unitIndex < this.size() - 1) {
            if(units[unitIndex].isOppositePolarity(units[unitIndex + 1])) {
                return unitIndex;
            }
            else {
                unitIndex++;
            }
        }

        return -1;
    }

    Polymer execute() {
        Polymer newPolymer = new Polymer(this.units);
        List<Unit> newUnits = new ArrayList(Arrays.asList(this.units));
        int firstIndexToDestroy = newPolymer.getIndexOfUnitToBeDestroyed();

        if (firstIndexToDestroy != -1) {
            newUnits.remove(firstIndexToDestroy);
            newUnits.remove(firstIndexToDestroy);
            newPolymer = new Polymer(newUnits.toArray(new Unit[newUnits.size() - 2]));
        }
        return newPolymer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this.size() != ((Polymer) o).size()) {
            return false;
        }
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(units);
    }
}
