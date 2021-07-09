package com.spj.authorization.register.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder(toBuilder = true)
@AllArgsConstructor
@Value
public class UserRegisterPayload implements Serializable {
    private String email;
    private String password;
    private Long authorityId;
    private boolean updatePasswordRequest;
}
