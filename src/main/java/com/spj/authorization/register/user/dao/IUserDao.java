package com.spj.authorization.register.user.dao;

import com.spj.authorization.security.entities.User;

import java.util.List;

/**
 * @author Yogesh Sharma
 */
public interface IUserDao {
    List<User> searchUserWithEmailAndAuthority(String email, String authority);

    //List<User> searchUserWithUserIdAndAuthority(Long userId, String authority);
}
