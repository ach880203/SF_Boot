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

    //공백도 허용하지 않는다!
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    //empty는 공백 문자열 허용한다 하지만 이메일 형식에선 안된다
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;  //회원 가입시 아이디로 사용~

    //empty는 공백 문자열 허용한다 하지만 길이문제에서 안된다
    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요")
    private String password;

    //empty는 공백 문자열 허용한다. 다른 조건은 없어서 허용된다.
    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;
}
