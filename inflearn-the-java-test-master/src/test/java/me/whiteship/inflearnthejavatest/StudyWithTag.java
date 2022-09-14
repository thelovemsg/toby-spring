package me.whiteship.inflearnthejavatest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class StudyWithTag {

    @Test
    @DisplayName("tag 테스트 fast 1")
    @Tag("fast")
    public void createStudyFast() {
        Study actual = new Study(100);
        Assertions.assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("tag 테스트 slow 1")
    @Tag("slow")
    public void createStudySlow() {
        Study actual = new Study(100);
        Assertions.assertThat(actual.getLimit()).isGreaterThan(0);
    }

}
