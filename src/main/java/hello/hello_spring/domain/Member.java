package hello.hello_spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //JPA가 관리하는 엔티티
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //PK, DB에서 값을 생성(Identity)
    private Long id;
    private String name;

    //각각의 정보에 대한 getter/setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
