package com.example.tuxBack.dto;

import com.example.tuxBack.domain.entity.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @Size(min=2, max=20)
    @NotEmpty(message = "사용자 ID는 필수항목입니다.")
    private String id;

    @Size(min=1, max=20)
    @NotEmpty(message = "사용자 이름은 필수항목입니다.")
    private String name;

    @Size(min=1, max=20)
    @NotEmpty(message = "학번은 필수항목입니다.")
    private String studentNum;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    @Email
    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;

    @Pattern(regexp="(01[016789])(\\\\d{3,4})(\\\\d{4})")
    @NotEmpty(message = "핸드폰 번호는 필수항목입니다.")
    private String phoneNum;

    private Boolean state;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .name(name)
                .studentNum(studentNum)
                .password(password1)
                .email(email)
                .phoneNum(phoneNum)
                .build();
    }

    @Builder
    public UserDto(String id, String name, String studentNum,
                   String password1, String password2, String email, String phoneNum, Boolean state) {
        this.id = id;
        this.name = name;
        this.studentNum = studentNum;
        this.password1 = password1;
        this.password2 = password2;
        this.email = email;
        this.phoneNum = phoneNum;
        this.state = state;
    }
}
