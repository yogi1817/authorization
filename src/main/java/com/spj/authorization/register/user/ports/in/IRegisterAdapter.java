package com.spj.authorization.register.user.ports.in;

import com.spj.authorization.security.entities.User;
import com.spj.register.openapi.resources.RegisterUserResponse;

public interface IRegisterAdapter {
    RegisterUserResponse registerUser(User userRegisterPayload);

    RegisterUserResponse updatePassword(User payload);
}
