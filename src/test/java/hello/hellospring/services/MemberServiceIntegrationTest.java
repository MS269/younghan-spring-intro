package hello.hellospring.services;

import hello.hellospring.domain.Member;
import hello.hellospring.repositories.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // Given
        Member member = new Member();
        member.setName("Hello");

        // When
        Long joinedId = memberService.join(member);

        // Then
        Member foundMember = memberService.findOne(joinedId).get();
        assertEquals(foundMember.getName(), member.getName());
    }

    @Test
    void 중복_회원_예외() {
        // Given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        // When
        memberService.join(member1);

        // Then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
    }

}
