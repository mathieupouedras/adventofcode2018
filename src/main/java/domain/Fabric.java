package domain;

import java.util.ArrayList;
import java.util.List;

public class Fabric {

    private final int size;
    private final List<Claim> claims;
    private final char[][] gridRepresentation;
    private final List<Claim>[][] conficts;


    public Fabric(int size, List<Claim> claims) {
        this.size = size;
        this.claims = claims;
        this.gridRepresentation = new char[size][size];
        conficts = new List[size][size];
    }

    String createRepresentation() {
        createEmptyGridRepresentation();

        for (Claim claim: claims) {
            for (int j = 0; j < claim.getHeight(); j++) {
                for (int i = 0; i < claim.getWidth(); i++) {
                    gridRepresentation[i + claim.getInchesToLeftEdge()][j + claim.getInchesToTopEdge()] = '#';
                    conficts[i + claim.getInchesToLeftEdge()][j + claim.getInchesToTopEdge()].add(claim);
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
                conficts[i][j] = new ArrayList();
            }
        }
    }

    @Override
    public String toString() {
        return createRepresentation();
    }


    int noConflict() {
        createRepresentation();

        for (Claim claim: claims) {
            boolean claimok = true;
            for (int j = 0; j < claim.getHeight(); j++) {
                for (int i = 0; i < claim.getWidth(); i++) {
                    if (conficts[i + claim.getInchesToLeftEdge()][j + claim.getInchesToTopEdge()].size() > 1) {
                        claimok = false;
                    }

                }
            }
            if (claimok) {
                return claim.getId();
            }
        }

        throw new RuntimeException("all claims have conflict");
    }

}
