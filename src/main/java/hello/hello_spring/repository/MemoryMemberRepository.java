package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository //스프링 컨테이너에 리포지토리 등록
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //회원이 저장될 HashMap
    private static long sequence = 0L; //생성될 회원의 id값(sequence)

    @Override //멤버를 리포지토리에 저장하는 메소드
    public Member save(Member member) {
        member.setId(++sequence); //sequence값 + 1로 id 설정
        store.put(member.getId(), member); //HashMap에 저장
        return member;
    }

    @Override //리포지토리에 있는 멤버를 id로 찾아서 반환
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //NULL이 반환될 경우를 대비해 Optional로 감싸서 처리
    }

    @Override //리포지토리에 있는 멤버를 name으로 찾아서 반환
    public Optional<Member> findByName(String name) {
        return store.values().stream() //stream을 이용해 람다식으로 처리
                .filter(member -> member.getName().equals(name)) //이름이 같은 Member 찾기
                .findAny(); //하나 찾으면 바로 Optional로 반환
    }

    @Override //저장된 멤버를 모두 반환
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //HashMap의 value 목록을 ArrayList로 반환
    }

    //afterEach용 메소드
    public void clearStore(){
        store.clear();
    }
}
