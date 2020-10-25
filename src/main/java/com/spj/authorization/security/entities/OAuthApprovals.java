package com.spj.authorization.security.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_approvals", schema = "auth", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class OAuthApprovals implements Serializable {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "active")
    private boolean active;

    @Column(name = "token")
    private byte[] token;

    @Column(name = "userid")
    private String userId;

    @Column(name = "clientid")
    private String clientId;

    @Column(name = "scope")
    private String scope;

    @Column(name = "status")
    private String status;

    @Column(name = "expiresat")
    private LocalDateTime expiresAt;

    @Column(name = "lastmodifiedat")
    private LocalDateTime lastModifiedAt;
}
