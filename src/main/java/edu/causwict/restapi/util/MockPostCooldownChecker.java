package edu.causwict.restapi.util;

public class MockPostCooldownChecker extends PostCooldownChecker{
    @Override
    public Boolean checkCooldown() { return true; }

    public MockPostCooldownChecker() {}
}
