package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//회원 서비스
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public long join(Member member){
        //이름 중복 검사 후 리포지토리에 멤버를 저장하고 id값을 반환
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //회원 가입 시 중복된 이름을 가진 회원 검증
    private void validateDuplicateMember(Member member) {
        //같은 이름을 가진 회원이 존재한다면 예외 발생
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //id값으로 멤버 찾기
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
