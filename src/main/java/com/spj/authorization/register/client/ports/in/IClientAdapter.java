package com.spj.authorization.register.client.ports.in;

import com.spj.register.openapi.resources.RegisterClientRequest;
import com.spj.register.openapi.resources.RegisterClientResponse;

public interface IClientAdapter {
    public RegisterClientResponse registerClient(RegisterClientRequest registerClientRequest);
}
