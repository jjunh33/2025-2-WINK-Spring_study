package hello.hello_spring.controller;

//html form에서 입력 받은 정보(이름)를 저장할 클래스
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
