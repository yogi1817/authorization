package com.spj.authorization.register.user.adapters;

import com.spj.authorization.security.entities.User;
import com.spj.register.openapi.resources.RegisterUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterMapper {
    User toEntity(RegisterUserRequest registerCustomerRequest);
}
