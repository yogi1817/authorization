package com.spj.authorization.security.repository;

import com.spj.authorization.security.entities.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yogesh Sharma
 */

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    Authorities findByAuthority(String authority);
}
