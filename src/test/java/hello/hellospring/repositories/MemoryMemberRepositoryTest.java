package hello.hellospring.repositories;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member = new Member();
        member.setName("Spring");
        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        assertEquals(result, member);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("Spring1").get();
        assertEquals(result, member1);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();
        assertEquals(result.size(), 2);
    }

}
