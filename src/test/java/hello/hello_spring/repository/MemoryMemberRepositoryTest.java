package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//MemoryMemberRepository 테스트용 클래스(다른데서 가져다 쓸 게 아니기 때문에 public으로 안해도 됨)
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메소드 실행이 끝날때마다 동작하는 메소드
    public void afterEach(){
        repository.clearStore();
    } //리포지토리 비우기

    @Test //save 메소드 테스트
    public void save(){

        //spring이라는 이름을 가진 멤버를 생성하고 리포지토리에 저장
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        //리포지토리에서 id값으로 생성한 멤버를 찾고, 이전에 생성한 멤버와 동일한지 확인
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); //Assertions.assertEquals(member, result);
    }

    @Test //findByName 메소드 테스트
    public void findByName(){

        //각각 spring1, spring2라는 이름을 가진 member1, member2를 생성하고 리포지토리에 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //리포지토리에서 spring1 이름을 가진 멤버를 찾고, member1과 동일한지 비교
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test //findAll 메소드 테스트
    public void findAll(){

        //각각 spring1, spring2라는 이름을 가진 member1, member2를 생성하고 리포지토리에 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //리포지토리에 있는 멤버를 모두 찾아서 리스트에 담고, 리스트의 size가 2인지 비교
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
