package com.example.tuxBack.controller;

import com.example.tuxBack.domain.entity.Message;
import com.example.tuxBack.dto.UserDto;
import com.example.tuxBack.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    // 회원가입 처리
    @PostMapping("/signup")
    public ResponseEntity<Message> execSignup(@RequestBody UserDto userDto) {
        if(!userDto.getPassword1().equals(userDto.getPassword2())){
            Message message = new Message("Fail", "비밀번호가 일치하지 않습니다.");

            return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
        }
        else{
            Long uid = userService.joinUser(userDto);
            Message message = new Message("Success", "회원가입에 성공하였습니다.", uid);

            return new ResponseEntity<Message>(message, HttpStatus.OK);
        }
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "/loginSuccess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }

}
