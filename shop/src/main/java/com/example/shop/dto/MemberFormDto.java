package com.example.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDto {

    //공백도 허용하지 않겠다.
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    //공백문자열 허용
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;  //회원 가입시 아이디로 사용~

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16 이하로 입력해주세요")
    private String password;

    //""
    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;
}
