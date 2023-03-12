package com.example.tuxBack.controller;

import com.example.tuxBack.domain.entity.Message;
import com.example.tuxBack.domain.entity.UserEntity;
import com.example.tuxBack.dto.IdCheckDto;
import com.example.tuxBack.dto.LoginDto;
import com.example.tuxBack.dto.UserDto;
import com.example.tuxBack.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/idCheck")
    public ResponseEntity<Boolean> checkId(@RequestBody IdCheckDto idCheckDto) {
        return ResponseEntity.ok(userService.checkIdDuplicate(idCheckDto.getId()));
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public ResponseEntity<Message> signup(@RequestBody UserDto userDto) {
        if(userService.checkEmailDuplicate(userDto.getEmail())){
            Message message = new Message("Fail", "이미 가입되어있는 이메일입니다.");
            return new ResponseEntity<Message>(message, HttpStatus.UNAUTHORIZED);
        }
        if(userService.checkStudentNumDuplicate(userDto.getStudentNum())){
            Message message = new Message("Fail", "이미 가입되어있는 학번입니다.");
            return new ResponseEntity<Message>(message, HttpStatus.UNAUTHORIZED);
        }
        if(!userDto.getPassword1().equals(userDto.getPassword2())){
            Message message = new Message("Fail", "비밀번호가 일치하지 않습니다.");
            return new ResponseEntity<Message>(message, HttpStatus.UNAUTHORIZED);
        }

        Long uid = userService.joinUser(userDto);
        Message message = new Message("Success", "회원가입에 성공하였습니다.", uid);
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<Message> login(@RequestBody LoginDto loginDto) {
        UserEntity entity = userService.findByUser(loginDto.getId());

        if(entity == null){
            Message message = new Message("Fail", "존재하지 않는 아이디입니다.");
            return new ResponseEntity<Message>(message, HttpStatus.UNAUTHORIZED);
        }
        if(!passwordEncoder.matches(loginDto.getPassword(), entity.getPassword())){
            Message message = new Message("Fail", "비밀번호가 일치하지 않습니다.");
            return new ResponseEntity<Message>(message, HttpStatus.UNAUTHORIZED);
        }

        Message message = new Message("Success", "로그인에 성공하였습니다.", entity.getId());
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout() {
        return "/logout";
    }

}
