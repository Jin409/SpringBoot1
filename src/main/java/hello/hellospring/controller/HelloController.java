package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //url 매칭되는 부분!
    @GetMapping("hello")
    public String hello(Model model){

        //html에서 data가 쓰임. -> hello로 출력됨.
        model.addAttribute("data","hello!");

        //resources 의 hello.html 과 동일하다
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name")String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody //http의 바디 부분에 직접 데이터를 넣어주겠다는 의미
    //소스보기를 하는 경우 데이터만 들어있음. HTML 관련된 태그들 전무
    public String helloString(@RequestParam("name") String name){
        return "hello"+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    // json으로 반환함
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        // 넣을 때
        public String getName() {
            return name;
        }

        // 꺼낼 때
        public void setName(String name) {
            this.name = name;
        }
    }


}
