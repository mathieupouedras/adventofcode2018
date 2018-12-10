package domain;

import infrastructure.FileClaimRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class FabricTest {

    @Test
    void should_print_an_empty_fabric_representation() {

        List<Claim> claims = new ArrayList<>();

        Fabric fabric = new Fabric(11, claims);

        String expectedFabricRepresentation =
                "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........";

        assertThat(fabric.toString(), is(expectedFabricRepresentation));
    }


    @Test
    void should_print_a_correct_fabric_representation() {
        Claim.ClaimBuilder claimBuilder =
                new Claim.ClaimBuilder().id(123)
                        .inchesToLeftEdge(3)
                        .inchesToTopEdge(2)
                        .width(5).height(4);

        Claim claim = claimBuilder.build();

        List<Claim> claims = new ArrayList<>();
        claims.add(claim);

        Fabric fabric = new Fabric(11, claims);

        String expectedFabricRepresentation =
                "...........\n" +
                        "...........\n" +
                        "...#####...\n" +
                        "...#####...\n" +
                        "...#####...\n" +
                        "...#####...\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........\n" +
                        "...........";

        assertThat(fabric.toString(), is(expectedFabricRepresentation));
    }

    @Test
    public void should_find_4_inches() {

        Claim.ClaimBuilder claimBuilder =
                new Claim.ClaimBuilder().id(1)
                        .inchesToLeftEdge(1).inchesToTopEdge(3)
                        .width(4).height(4);

        Claim claim1 = claimBuilder.build();

        claimBuilder =
                new Claim.ClaimBuilder().id(2)
                        .inchesToLeftEdge(3).inchesToTopEdge(1)
                        .width(4).height(4);

        Claim claim2 = claimBuilder.build();

        claimBuilder =
                new Claim.ClaimBuilder().id(3)
                        .inchesToLeftEdge(5).inchesToTopEdge(5)
                        .width(2).height(2);

        Claim claim3 = claimBuilder.build();

        List<Claim> claims = new ArrayList<>();
        claims.add(claim1);
        claims.add(claim2);
        claims.add(claim3);

        Fabric fabric = new Fabric(11, claims);

        System.out.println(fabric.noConflict());
    }

    @Test
    public void part1() {
        ClaimRepository claimRepository = new FileClaimRepository();
        List<Claim> claims = claimRepository.readAll();

        Fabric fabric = new Fabric(1000, claims);
        fabric.createRepresentation();

        //System.out.println(fabric.computeConflictInches());

    }

    @Test
    public void part2() {
        ClaimRepository claimRepository = new FileClaimRepository();
        List<Claim> claims = claimRepository.readAll();

        Fabric fabric = new Fabric(1000, claims);

        System.out.println(fabric.noConflict());

    }


}
