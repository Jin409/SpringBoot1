package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
