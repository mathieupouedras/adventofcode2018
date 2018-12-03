package domain;

import java.util.List;

public interface ClaimRepository {
    List<Claim> readAll();
}
