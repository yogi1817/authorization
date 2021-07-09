package com.spj.authorization.register.user.endpoints;

import com.spj.authorization.register.user.adapters.RegisterMapper;
import com.spj.authorization.register.user.ports.in.IRegisterAdapter;
import com.spj.register.openapi.endpoint.RegisterUserApi;
import com.spj.register.openapi.endpoint.RegisterUserApiDelegate;
import com.spj.register.openapi.resources.RegisterUserRequest;
import com.spj.register.openapi.resources.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RegisterController implements RegisterUserApi {
    private final IRegisterAdapter registerAdapter;
    private final RegisterMapper registerMapper;

    @Override
    public ResponseEntity<RegisterUserResponse> registerUser(RegisterUserRequest registerUserRequest) {
        log.debug("Inside BarberController registerBarber service");
        if(registerUserRequest.getUpdatePasswordRequest()){
            return ResponseEntity.ok(registerAdapter.updatePassword(registerMapper.toEntity(registerUserRequest)));
        }else{
            return ResponseEntity.ok(registerAdapter.registerUser(registerMapper.toEntity(registerUserRequest)));
        }
    }

    /*@Override
    public ResponseEntity<RegisterUserResponse> updatePassword(@Valid RegisterUserRequest registerUserRequest) {
        log.debug("Inside BarberController registerBarber service");
        return ResponseEntity.ok(registerAdapter.registerUser(registerUserRequest));
    }*/
}
