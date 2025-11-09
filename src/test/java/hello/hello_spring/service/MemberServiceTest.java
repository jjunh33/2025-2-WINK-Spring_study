package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //테스트 실행 전 호출되어 테스트마다 항상 새로운 객체를 생성
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach //각 테스트의 독립적인 실행을 위해 테스트 실행이 끝날때마다 리포지토리 초기화
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //hello라는 이름을 가진 멤버 생성(given)
        Member member = new Member();
        member.setName("hello");

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
//        try{
//            memberService.join(member2);
//            fail();
//        } catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

/*
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
 */
}