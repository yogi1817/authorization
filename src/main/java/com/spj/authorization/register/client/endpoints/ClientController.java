package com.spj.authorization.register.client.endpoints;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.spj.authorization.register.client.ports.in.IClientAdapter;
import com.spj.register.openapi.endpoint.RegisterClientApi;
import com.spj.register.openapi.resources.RegisterClientRequest;
import com.spj.register.openapi.resources.RegisterClientResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ClientController implements RegisterClientApi {
    private final IClientAdapter clientAdapter;

    @Override
    public ResponseEntity<RegisterClientResponse> registerClient(@Valid RegisterClientRequest registerClientRequest) {
        log.info("Inside ClientController registerClient service");
        return ResponseEntity.ok(clientAdapter.registerClient(registerClientRequest));
    }
}
