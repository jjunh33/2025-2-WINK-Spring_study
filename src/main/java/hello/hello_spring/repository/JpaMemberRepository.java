package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //JPA는 EntityManager로 모든것이 동작, 주입 받아야함
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //영속성 컨텍스트에 엔티티 add
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //id값으로 멤버 찾기
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //name값으로 멤버 찾기
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //엔티티(Member 객체)를 대상으로 쿼리를 날리면 sql로 번역됨
        return em.createQuery("select m from Member m", Member.class)
                    .getResultList();
   }
}
