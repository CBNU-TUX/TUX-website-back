package com.example.tuxBack.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

    @Size(min=2, max=20)
    @NotEmpty(message = "사용자 ID는 필수항목입니다.")
    private String id;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

    @Builder
    public LoginDto(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
