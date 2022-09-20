package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 하나의 인스턴그스 공유
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepeatTest {

    int value = 1;

    @BeforeAll
    void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    void afterAll() {
        System.out.println("after all");
    }

    @DisplayName("반복 테스트")
    @Order(1)
    @RepeatedTest(value = 10, name = "{displayName},{currentRepetition}/{totalRepetitions}")
    void repeat_test(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @Order(3)
    @DisplayName("스터기 만들기2")
    @ParameterizedTest(name="{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가","많이","추워지고","추어탕먹자"})
    void parameterizedTest(String message){
        System.out.println(message);
    }

    @Order(2)
    @DisplayName("스터기 만들기3")
    @ParameterizedTest(name="{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가","많이","추워지고","추어탕먹자"})
//    @EmptySource
//    @NullSource
    @NullAndEmptySource
    void parameterizedTest1(String message){
        System.out.println(message);
    }

    @DisplayName("CsvSource 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10,20,40})
    void parameterizedTest2(@ConvertWith(StudyConverter.class) Study study){
        System.out.println(study.getLimit());
    }

    @DisplayName("CsvSource 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void parameterizedTest3(Integer limit, String name){
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("CsvSource 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void parameterizedTest4(ArgumentsAccessor argumentsAccessor){
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
    }

    @DisplayName("CsvSource 테스트")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void parameterizedTest5(@AggregateWith(StudyAggregator.class) Study study){
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator{
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }

    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object o, Class<?> targetType) throws ArgumentConversionException {
            Assertions.assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(o.toString()));
        }
    }

}
