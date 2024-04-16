package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(@RequestParam(name="name", required=false) String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

    static class Hello{
        private String name;
        public String getName() { return name; }
        public void setName(String n) { name = n; }
    }
    @GetMapping("helloapi")
    @ResponseBody
    public Hello helloApi(@RequestParam(name="name", required=false) String name){
        Hello h = new Hello();
        h.setName(name);
        return h;
    }

}
