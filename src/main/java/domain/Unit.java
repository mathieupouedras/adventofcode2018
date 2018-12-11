package domain;

class Unit {

    private final char representation;
    private final String stringRepresentation;

    Unit(char representation) {
        this.representation = representation;
        this.stringRepresentation = String.valueOf(representation);
    }

    boolean isSameType(Unit otherUnit) {
        return this.stringRepresentation.equalsIgnoreCase(otherUnit.stringRepresentation);
    }

    boolean isOppositePolarity(Unit otherUnit) {
        return this.isSameType(otherUnit) && !this.stringRepresentation.equals(otherUnit.stringRepresentation);
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }
}
