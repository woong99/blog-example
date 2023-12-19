package org.example.jspexception.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.jspexception.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/user")
    public String user(HttpServletRequest request) {
        UserDto user = UserDto.builder()
                .userId("potatowoong")
                .userName("포테이토웅")
                .build();
        request.getSession().setAttribute("user", user);
        return "user";
    }

    @GetMapping("/no-user")
    public String noUser(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "user";
    }
}
