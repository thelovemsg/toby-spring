package me.whiteship.inflearnthejavatest;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class StudyJUnit4Test {

    @Before
    public void before () {
        System.out.println("before");
    }

    @Test
    public void createStudy(){
        System.out.println("test");
    }

    @Test
    public void createStudy2(){
        System.out.println("test2");
    }

}
