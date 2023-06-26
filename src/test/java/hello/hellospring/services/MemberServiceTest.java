package hello.hellospring.services;

import hello.hellospring.domain.Member;
import hello.hellospring.repositories.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

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

//        try {
//            service.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e) {
//            assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
//        }
    }

}
