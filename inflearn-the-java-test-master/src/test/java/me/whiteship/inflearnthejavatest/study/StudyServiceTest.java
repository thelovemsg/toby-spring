package me.whiteship.inflearnthejavatest.study;

import me.whiteship.inflearnthejavatest.domain.Member;
import me.whiteship.inflearnthejavatest.domain.Study;
import me.whiteship.inflearnthejavatest.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import javax.swing.text.html.Option;
import javax.xml.parsers.SAXParser;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Test
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("thelovemsg@tetet.com");
        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Study study = new Study(10, "테스트");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        BDDMockito.given(memberService.findById(1L)).willReturn(Optional.of(member));
        BDDMockito.given(studyRepository.save(study)).willReturn(study);

        studyService.createNewStudy(1L, study);
        verify(memberService, times(1)).notify(study);
        verify(memberService, times(1)).notify(member);
        verify(memberService, never()).validate(any());

        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);

        /*Optional<Member> byId = memberService.findById(1L);
        Assertions.assertEquals("thelovemsg@tetet.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(3L));*/

    }

    public static void main(String[] args) {
        int[] nums = {1,5,1,2,4,5,1,7};
        int cnt[] = new int[101], pairs = 0;
        for (int n : nums) {
            int temp = ++cnt[n];
            System.out.println("temp = " + temp);
            pairs += temp % 2 == 0 ? 1 : 0;
        }
        int[] result = new int[] { pairs, nums.length - pairs * 2 };
        for (int i : result) {
            System.out.println("i = " + i);
        }

        boolean[] map = new boolean[101];
        int pair = 0;
        int diff = 0;

        for (int item : nums) {
            if (map[item]) {
                diff--;
                pair++;
            } else
                diff++;
            map[item] = !map[item];
        }

    }

}