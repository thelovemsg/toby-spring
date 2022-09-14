package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void create() {
        App app = new App();
        Assertions.assertNotNull(app);
    }
}
