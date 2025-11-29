package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행
@Transactional //테스트 실행 시 트랜잭션을 시작하고 테스트 수행 후 해당 데이터를 롤백(커밋 x)
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //spring이라는 이름을 가진 멤버 생성(given)
        Member member = new Member();
        member.setName("spring");

        //해당 멤버 회원가입(when)
        Long saveId = memberService.join(member);

        //가입된 멤버가 given에서 생성된 멤버와 동일한지 확인(then)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //같은 이름의 멤버 2개 생성(given)
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //spring이라는 이름을 가진 멤버가 이미 리포지토리에 있을때(when)
        memberService.join(member1);

        //같은 이름의 멤버를 회원가입 시키면 에러가 발생하는지 확인(then)
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}