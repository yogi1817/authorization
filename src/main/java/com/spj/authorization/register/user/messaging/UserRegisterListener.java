package com.spj.authorization.register.user.messaging;

import com.spj.authorization.register.user.ports.in.IRegisterAdapter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UserRegisterListener {

    private final IRegisterAdapter registerAdapter;

    @StreamListener(UserRegisteredSink.INPUT)
    @SneakyThrows
    public void userRegisterRequest(UserRegisterPayload payload){
        if(payload.isUpdatePasswordRequest()){
            registerAdapter.updatePassword(payload);
        }else{
            registerAdapter.registerUser(payload);
        }
    }
}
