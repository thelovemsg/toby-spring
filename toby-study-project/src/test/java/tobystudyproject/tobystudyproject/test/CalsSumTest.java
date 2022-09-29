package tobystudyproject.tobystudyproject.test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CalsSumTest {

    Calculator calculator;
    String numFilepath;

    @BeforeAll
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("number.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException{
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum(getClass().getResource("tobystudyproject/tobystudyproject/test/number.txt").getPath());
        Assertions.assertThat(sum).isEqualTo(10);
    }

}
