package com.sboard.controller;

import com.sboard.config.AppInfo;
import com.sboard.dto.UserCheck;
import com.sboard.dto.UserDto;
import com.sboard.entity.User;
import com.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AppInfo appInfo;

//    @Value("${spring.application.version}")
//    private String appVersion;

    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("appVersion", appInfo);
        return "user/login";
    }

    @GetMapping("/terms")
    public String terms() {
        return "user/terms";
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity register(@RequestBody UserDto userDto) {
        log.info(userDto);
        User user = userDto.toEntity();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);
    }

    @PostMapping("/check-user")
    public ResponseEntity<?> checkUser(@RequestBody UserCheck userCheck) {
        boolean uidExists = userService.checkUid(userCheck.getUidExists());
        boolean emailExists = userService.checkEmail(userCheck.getEmailExists());
        boolean nickExists = userService.checkNick(userCheck.getNickExists());

        Map<String, Boolean> response = new HashMap<>();
        response.put("uidExists", uidExists);
        response.put("emailExists", emailExists);
        response.put("nickExists", nickExists);

        return ResponseEntity.ok(response);
    }

//    @GetMapping("/check-email")
//    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String value) {
//        boolean email = userService.checkEmail(value);
//        Map<String, Boolean> response = new HashMap<>();
//
//        response.put("exists", email);
//        return ResponseEntity.ok(response);
//    }

}
