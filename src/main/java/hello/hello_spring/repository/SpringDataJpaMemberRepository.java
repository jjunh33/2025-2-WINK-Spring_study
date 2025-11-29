package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository<엔티티, PK>, MemberRepository 인터페이스를 다중 상속
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
