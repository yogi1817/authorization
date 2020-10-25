package com.spj.authorization.register.user.ports.in;

import com.spj.register.openapi.resources.*;

public interface IRegisterAdapter {
    RegisterBarberResponse registerBarber(RegisterBarberRequest registerBarberRequest);

    RegisterCustomerResponse registerCustomer(RegisterCustomerRequest registerBarberRequest);
}
