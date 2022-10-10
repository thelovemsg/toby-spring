package tobystudyproject.tobystudyproject.pointcut;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import static org.assertj.core.api.Assertions.*;

public class PointcutExpressionTest {
    @Test
    public void methodSignaturePointcut() throws SecurityException, NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public int "
                + "tobystudyproject.tobystudyproject.pointcut.Target.minus(int, int) "
                + "throws java.lang.RuntimeException)");

        //Target.minus()
        assertThat(
            pointcut.getClassFilter().matches(Target.class)
            && pointcut.getMethodMatcher().matches(Target.class.getMethod("minus", int.class, int.class), null)).isEqualTo(true);

        //Target.plus()
        assertThat(pointcut.getClassFilter().matches(Target.class) && pointcut.getMethodMatcher().matches(
            Target.class.getMethod("plus",  int.class, int.class), null)).isEqualTo(false);

        //Bean.method()
        assertThat(pointcut.getClassFilter().matches(Bean.class) && pointcut.getMethodMatcher().matches(
                Target.class.getMethod("method"), null
        )).isEqualTo(false);
    }

    @Test
    public void pointcut() throws Exception {
        targetClassPointcutMatches("execution(* *(..))", true, true, true, true, true, true);
    }

    public void pointcutMatches(String expression, Boolean expected, Class<?> clazz,
                                String methodName, Class<?>... args) throws Exception {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        assertThat(pointcut.getClassFilter().matches(clazz)
            && pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null)).isEqualTo(expected);
    }

    public void targetClassPointcutMatches(String expression, boolean... expected) throws Exception {
        pointcutMatches(expression, expected[0], Target.class, "hello");
        pointcutMatches(expression, expected[1], Target.class, "hello", String.class);
        pointcutMatches(expression, expected[2], Target.class, "plus", int.class, int.class);
        pointcutMatches(expression, expected[3], Target.class, "minus", int.class, int.class);
        pointcutMatches(expression, expected[4], Target.class, "method");
        pointcutMatches(expression, expected[5], Bean.class, "method");
    }
}
