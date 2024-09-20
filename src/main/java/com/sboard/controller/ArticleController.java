package com.sboard.controller;

import com.sboard.dto.UserDto;
import com.sboard.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final UserService userService;


    @GetMapping("/")
    public @ResponseBody List<UserDto> list(HttpServletResponse response) {
        List<UserDto> users = userService.selectUsers();
        return users;
    }



    @GetMapping("/write")
    public String write() {
        return "article/write";
    }



    @GetMapping("/view")
    public String view() {
        return "article/view";
    }



    @GetMapping("/modify")
    public String modify() {
        return "article/modify";
    }




}
