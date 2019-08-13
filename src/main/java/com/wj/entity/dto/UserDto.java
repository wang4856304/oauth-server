package com.wj.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author jun.wang
 * @title: UserDto
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/8/6 15:46
 */

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "用户名为空")
    private String username;

    @NotBlank(message = "密码为空")
    private String password;
}
