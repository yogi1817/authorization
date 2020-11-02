package com.spj.authorization.register.user.adapters;

import com.spj.authorization.register.user.messaging.UserRegisterPayload;
import com.spj.authorization.security.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterMapper {
    User toEntity(UserRegisterPayload registerCustomerRequest);
}
