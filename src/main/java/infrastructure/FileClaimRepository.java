package infrastructure;

import domain.Claim;
import domain.ClaimRepository;
import domain.LineReader;

import static domain.Claim.ClaimBuilder;

import java.util.ArrayList;
import java.util.List;

public class FileClaimRepository implements ClaimRepository {

    private final LineReader lineReader;

    public FileClaimRepository() {
        lineReader = new FileLineReader();
    }


    @Override
    public List<Claim> readAll() {
        List<Claim> claims = new ArrayList<>();

        String[] lines = lineReader.read("input.txt");

        for (String line : lines) {
            ClaimBuilder claimBuilder = new ClaimBuilder();
            String[] tokens = line.split(" ");
            String id = tokens[0];
            String position = tokens[2];
            String dimension = tokens[3];

            claimBuilder.inchesToLeftEdge(Integer.valueOf(position.substring(0, position.indexOf(','))));
            claimBuilder.inchesToTopEdge(Integer.valueOf(position.substring(position.indexOf(',') + 1, position.length() -1)));

            claimBuilder.width(Integer.valueOf(dimension.split("x")[0]));
            claimBuilder.height(Integer.valueOf(dimension.split("x")[1]));

            claimBuilder.id(Integer.valueOf(id.substring(1, id.length())));

            claims.add(claimBuilder.build());
        }

        return claims;
    }
}
