package tobystudyproject.tobystudyproject.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnitTest {

    static JUnitTest testObject;

    @Test
    public void test1() {
        Assertions.assertEquals(this, testObject);
        testObject = this;
    }

    @Test
    public void test2() {
        Assertions.assertEquals(this, testObject);
        testObject = this;
    }

    @Test
    public void test3() {
        Assertions.assertEquals(this, testObject);
        testObject = this;
    }
}
