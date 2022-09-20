package me.whiteship.inflearnthejavatest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

//@ExtendWith(FindSlowTestExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StudyWithCustomAnno {

    int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_study_fast() throws InterruptedException {
        Thread.sleep(2000L);
        Study study = new Study(100);
        Assertions.assertThat(study.getLimit()).isGreaterThan(0);
    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void create_study_slow() {
        Study study = new Study(100);
        Assertions.assertThat(study.getLimit()).isGreaterThan(0);
    }


}
