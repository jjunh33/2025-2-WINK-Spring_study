package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // controller가 클라이언트의 요청을 받아 처리
public class HelloController {

    @GetMapping("hello") // localhost:8080//hello
    public String hello(Model model){
        model.addAttribute("data", "hi!!");
        return "hello";
    }

    //MVC
    @GetMapping("hello-mvc") // localhost:8080//hello-mvc?name:spring
    public String helloMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template"; // 템플릿 엔진(thymeleaf)로 변환 후 반환
    }

    //API(String)
    @GetMapping("hello-string")
    @ResponseBody // ResponseBody 어노테이션이 붙어있다면 받은 값을 viewResolver가 아닌 HttpMessageConverter로 넘김
    public String helloString(@RequestParam("name") String name){ // ? 뒤에 할당된 값을 받아서 model을 통해 viewResolver로 넘김
        return "hello " + name; // string을 반환(StringConverter)
    }

    //API(객체)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 객체를 반환(JsonConverter)
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
