package com.spj.authorization.register.user.messaging;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = {UserRegisteredSink.class})
public class UserRegisterBinder {
}
