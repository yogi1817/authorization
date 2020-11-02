package com.spj.authorization.register.user.ports.in;

import com.spj.authorization.register.user.messaging.UserRegisterPayload;

public interface IRegisterAdapter {
    void registerUser(UserRegisterPayload userRegisterPayload);

    void updatePassword(UserRegisterPayload payload);
}
