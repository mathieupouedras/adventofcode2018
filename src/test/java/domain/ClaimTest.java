package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.*;

import static domain.Claim.ClaimBuilder;

class ClaimTest {

    @Test
    void should_build_claim_with_id_123() {
        ClaimBuilder claimBuilder =
                new ClaimBuilder().id(123);
        Claim claim = claimBuilder.build();

        assertThat(claim.getId(), is(123));
    }
}
