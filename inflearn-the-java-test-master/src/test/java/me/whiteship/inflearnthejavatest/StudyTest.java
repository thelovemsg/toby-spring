package me.whiteship.inflearnthejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기")
    void create() {
        Study study = new Study(20);

//        assertNotNull(study);
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 DRAFT 상태다.");
//        assertTrue(study.getLimit() > 0, "스터디 최대 참석 인원은 0보다 커야 한다");

        //assertAll => 연관된 테스트를 한번에 전부 실행해보리기~
        assertAll(() -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 DRAFT 상태다."),
                () -> assertTrue(study.getLimit() >0, "스터디 최대 참석 인원은 0보다 커야 한다."));

        //에러 던져서 테스트 해 보리기~
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals(message,"limit은 0보다 커야 한다.");

        //걸리는 시간도 측정 가능하다!
        assertTimeout(Duration.ofSeconds(1), () -> {
            new Study(10);
            Thread.sleep(300);
        });

        //다른 스타일로 테스트 코드도 작성이 가능하다~
        Study actual = new Study(20);
        assertThat(actual.getLimit()).isGreaterThan(0);

    }

    @Test
    @DisplayName("스터디 만들기1")
    void create1() {
        System.out.println("create1");
    }

    @Test
    void createStudy(){
        String test_env = System.getenv("TEST_ENV");
        System.out.println("test_env = " + test_env);

        assumingThat(test_env ==null, () -> {
            System.out.println("null");
            Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

//        assumeTrue("LOCAL".equalsIgnoreCase(test_env));
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

        assumingThat("thelovemsg".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });


        Study actual = new Study(20);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @EnabledOnOs(OS.WINDOWS) //여러개 입력 원하면 중괄호로 감싸서 표기해주면 된다.
//    @EnabledOnJre(JRE.JAVA_11)
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "LOCAL")
    void createStudyWithEnabled(){
        String test_env = System.getenv("TEST_ENV");
        System.out.println("test_env = " + test_env);

        assumingThat(test_env ==null, () -> {
            System.out.println("null");
            Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

//        assumeTrue("LOCAL".equalsIgnoreCase(test_env));
        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });

        assumingThat("thelovemsg".equalsIgnoreCase(test_env), () -> {
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });


        Study actual = new Study(20);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    // 모든 테스트를 실행하기 전에 반드시 딱 한번만 호출됨
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    // 모든 테스트를 실행한 이후에 반드시 딱 한번만 호출됨
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    //모든 테스트를 실행할 때 각각의 테스트를 실행하기 이전에 실행된다.
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}