package edu.causwict.restapi.util;

import java.time.Duration;
import java.time.Instant;

public class PostCooldownChecker {

    private Instant last_created = null;

    public Boolean checkCooldown() {
        if (last_created == null) return true;
        return Duration.between(last_created, Instant.now())
                .compareTo(Duration.ofMinutes(3)) > 0;
    }

    public void resetCooldown() {
        this.last_created = Instant.now();
    }

    public PostCooldownChecker() {}
}
