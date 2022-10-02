package tobystudyproject.tobystudyproject.caltest;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import tobystudyproject.tobystudyproject.section_three.cal.Calculator;

import java.io.IOException;

public class CalcSumTest {
    Calculator calculator;
    String numFilepath;

    @Before
    public void setUp(){
        this.calculator = new Calculator();
        this.numFilepath = "D:\\numbers.txt";
    }

    @Test
    public void sumOfNumbers() throws IOException {
        Assertions.assertThat(calculator.calcSum(this.numFilepath)).isEqualTo(15);
    }

    @Test
    public void sumOfMultiply() throws IOException {
        Assertions.assertThat(calculator.calcMultiply(this.numFilepath)).isEqualTo(15);
    }

    @Test
    public void concatenateStrings() throws IOException {
        Assertions.assertThat(calculator.concatenate(this.numFilepath)).isEqualTo("1234");
    }
}
