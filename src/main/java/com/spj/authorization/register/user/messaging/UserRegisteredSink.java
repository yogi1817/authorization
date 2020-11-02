package com.spj.authorization.register.user.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserRegisteredSink {
    String INPUT = "user-registered-in";

    @Input(INPUT)
    SubscribableChannel input();
}
