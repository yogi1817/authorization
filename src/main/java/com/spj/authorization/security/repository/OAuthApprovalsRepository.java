package com.spj.authorization.security.repository;

import com.spj.authorization.security.entities.OAuthApprovals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yogesh Sharma
 */

@Repository
public interface OAuthApprovalsRepository extends JpaRepository<OAuthApprovals, Long> {
    OAuthApprovals findByCode(String code);
}
