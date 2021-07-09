package com.spj.authorization.security.pojo;

import com.spj.authorization.security.utils.AuthorityUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.User;

/**
 * @author Yogesh Sharma
 */
@Data
@EqualsAndHashCode
public class CustomUser extends User {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String email;
    private int failedLoginAttempt;
    private boolean accountLocked;
    private boolean isBlocked;

    public CustomUser(com.spj.authorization.security.entities.User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.getUserAuthorities(user));
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.failedLoginAttempt = user.getFailedLoginAttempt();
        this.accountLocked = user.isLocked();
        this.isBlocked = user.isBlocked();
    }
}