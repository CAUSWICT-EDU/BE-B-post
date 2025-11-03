package edu.causwict.restapi.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private final AtomicLong sequence = new AtomicLong(0);

    public Long getSequence() {
        return sequence.incrementAndGet();
    }

    public Long getCurrentSequence() {
        return sequence.get();
    }
}
