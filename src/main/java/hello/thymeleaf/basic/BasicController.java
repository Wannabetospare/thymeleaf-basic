package hello.thymeleaf.basic;


import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 컨트롤러 - String 형으로 반환하면 반환값으로 주소를 이동시켜줌
@Controller
// 리퀘스트 매핑 - 겹치는 공통주소를 먼저 선언해준다.
@RequestMapping("/basic")
public class BasicController {


    // 매핑 종류 - GET
    // 매핑 주소 - /basic/text-basic
    // 이름 - textBasic
    // 매개변수 - model
    // 동작 - data 에 Spring Start! 문자열을 model 에 담아서 전달한다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Spring Start");
        return "basic/text-basic";
    }


    // 매핑 종류 - GET
    // 매핑 주소 - /basic/text-unescaped
    // 이름 - textUnescaped
    // 매개변수 - model
    // 동작 - data 에 "Hello <b>Spring!</b>" 문자열을 담아서 모델에 넘긴다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    // 매핑 종류 - GET
    // 매핑 주소 - /basic/variable
    // 이름 - variable
    // 매개변수 - model
    // 동작 - User 를 만들어서 리스트로 저장하고, map 으로도 저장한다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);
        return "basic/variable";
    }


    @Data
    static class User {
        private String username;
        private int age;
        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }


    // 매핑 종류 - GET
    // 매핑 주소 - /basic/basic-objects
    // 이름 - basicObjects
    // 매개변수 - session
    // 동작 - session 부에 Hello Session 문자형을 넘긴다.
    // 반환 - 주소를 반환한다.
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    // 컴포넌트로 스프링 컨테이너에 스프링 빈으로등록 - 컴포넌트 스캔에 걸림
    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }


    // 매핑 종류 - GET
    // 매핑 주소 - /basic/date
    // 이름 - date
    // 매개변수 - model
    // 동작 - localDateTiem 에 지역, 날짜, 시간을 담에 모델로 넘긴다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }


    // 매핑 종류 - GET
    // 매핑 주소 - /basic
    // 이름 - link
    // 매개변수 - model
    // 동작 - param1, param2 에 각각 data1, data2 를 담는다.
    // 반환 - 주소값을 반환한다
    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    // 매핑 종류 - GET
    // 매핑 주소 - /basic/literal
    // 이름 - literal
    // 매개변수 - model
    // 동작 - data 에 Spring! 문자열을 모델로 넘긴다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    // 매핑 종류 - GET
    // 매핑 주소 - /basic/operation
    // 이름 - operation
    // 매개변수 - model
    // 동작 - nullData 값에 null 값을 담고, data 에 Spring! 을 담아서 모델로 넘긴다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    // 매핑 종류 - GET
    // 매핑 주소 - /basic/attribute
    // 이름 - attribute
    // 매개변수 - x
    // 동작 - x
    // 반환 - 주소값 반환
    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }


    // 매핑 종류 - GET
    // 매핑 주소 - /basic/each
    // 이름 - each
    // 매개변수 - model
    // 동작 - addUsers 메서드에 매개변수 model 을 담는다.
    // 반환 -
    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    // 이름 - addUsers
    // 매개변수 - model
    // 동작 - 리스트를 만들고 userA, B, C 를 생성하여 리스트에 넣는다.
    // 반환값 - x
    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    // 매핑 종류 - GET
    // 매핑 주소 - /basic/condition
    // 이름 - condition
    // 매개변수 - model
    // 동작 - addUsers 에 model 을 담는다.
    // 반환 - 주소값 반환
    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    // 매핑 종류 - GET
    // 매핑 주소 - /basic/comments
    // 이름 - comments
    // 매개변수 - model
    // 동작 - data 에 Spring! 문자열을 담아서 모델로 전달한다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    // 매핑 종류 - GET
    // 매핑 주소 - /basic/block
    // 이름 - block
    // 매개변수 - model
    // 동작 - addUser 메서드에 model 을 넣는다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    // 매핑 종류 - GET
    // 매핑 주소 - /basic/javascript
    // 이름 - javascript
    // 매개변수 - model
    // 동작 - user 와 User를 모델에 담아서 전달한다.
    // 반환 - 주소값을 반환한다.
    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);
        return "basic/javascript";
    }







}
