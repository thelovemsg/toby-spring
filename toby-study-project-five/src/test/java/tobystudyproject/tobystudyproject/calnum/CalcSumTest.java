package tobystudyproject.tobystudyproject.calnum;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CalcSumTest {

    Calculator calculator;
    String numFilePath = "D:\\numbers.txt";

    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        Assertions.assertThat(calculator.calcSum(this.numFilePath)).isEqualTo(10);
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        Assertions.assertThat(calculator.calcMultiply(this.numFilePath)).isEqualTo(24);
    }
}
