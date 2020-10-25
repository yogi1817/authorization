package com.spj.authorization.register.user.endpoints;

import com.spj.authorization.register.user.ports.in.IRegisterAdapter;
import com.spj.register.openapi.endpoint.RegisterUserApiDelegate;
import com.spj.register.openapi.resources.RegisterBarberRequest;
import com.spj.register.openapi.resources.RegisterBarberResponse;
import com.spj.register.openapi.resources.RegisterCustomerRequest;
import com.spj.register.openapi.resources.RegisterCustomerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RegisterController implements RegisterUserApiDelegate {
    private final IRegisterAdapter registerAdapter;

    @Override
    public ResponseEntity<RegisterBarberResponse> registerBarber(RegisterBarberRequest registerBarberRequest) {
        log.debug("Inside BarberController registerBarber service");
        return ResponseEntity.ok(registerAdapter.registerBarber(registerBarberRequest));
    }

    @Override
    public ResponseEntity<RegisterCustomerResponse> registerCustomer(RegisterCustomerRequest registerCustomerRequest) {
        log.info("Inside CustomerController registerBarber service");
        return ResponseEntity.ok(registerAdapter.registerCustomer(registerCustomerRequest)
                .message("Member registered successfully"));
    }
}
