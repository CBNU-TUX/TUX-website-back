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
public class IdCheckDto {
    @Size(min=2, max=20)
    @NotEmpty(message = "사용자 ID는 필수항목입니다.")
    private String id;

    @Builder
    public IdCheckDto(String id) {
        this.id = id;
    }
}
