package com.spj.authorization.security.repository;

import com.spj.authorization.security.entities.OAuthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientsDetailsRepository extends JpaRepository<OAuthClientDetails, String> {
    OAuthClientDetails findByClientId(String clientId);
}
