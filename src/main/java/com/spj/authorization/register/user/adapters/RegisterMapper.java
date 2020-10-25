package com.spj.authorization.register.user.adapters;

import com.spj.authorization.security.entities.User;
import com.spj.register.openapi.resources.RegisterBarberRequest;
import com.spj.register.openapi.resources.RegisterBarberResponse;
import com.spj.register.openapi.resources.RegisterCustomerRequest;
import com.spj.register.openapi.resources.RegisterCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterMapper {
    User toEntity(RegisterCustomerRequest registerCustomerRequest);

    RegisterCustomerResponse toResponse(User register);

    User toEntity(RegisterBarberRequest registerBarberRequest);

    RegisterBarberResponse toBarberResponse(User register);
}
