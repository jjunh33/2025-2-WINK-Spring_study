package hello.hello_spring.domain;

//Member 클래스
public class Member {

    //회원 정보(id, 이름)
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
