package domain;

import java.util.List;

public class Fabric {

    private final int size;
    private final List<Claim> claims;
    private final char[][] gridRepresentation;
    private final int[][] conficts;


    public Fabric(int size, List<Claim> claims) {
        this.size = size;
        this.claims = claims;
        this.gridRepresentation = new char[size][size];
        conficts = new int[size][size];
    }

    String createRepresentation() {
        createEmptyGridRepresentation();

        for (Claim claim: claims) {
            for (int j = 0; j < claim.getHeight(); j++) {
                for (int i = 0; i < claim.getWidth(); i++) {
                    gridRepresentation[i + claim.getInchesToLeftEdge()][j + claim.getInchesToTopEdge()] = '#';
                    conficts[i + claim.getInchesToLeftEdge()][j + claim.getInchesToTopEdge()] = conficts[i + claim.getInchesToLeftEdge()][j + claim.getInchesToTopEdge()] + 1;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                stringBuilder.append(gridRepresentation[j][i]);
            }
            if (i != size -1) {
                stringBuilder.append('\n');
            }
        }


        return stringBuilder.toString();
    }

    private void createEmptyGridRepresentation() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gridRepresentation[j][i] = '.';
            }
        }
    }

    @Override
    public String toString() {
        return createRepresentation();
    }

    int computeConflictInches() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j =0; j < size; j++) {
                if (conficts[i][j] > 1) {
                    count++;
                }
            }
        }
        return count;
    }

}
