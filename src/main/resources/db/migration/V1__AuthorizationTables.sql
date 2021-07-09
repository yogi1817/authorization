create table auth.authorities(
    authority_id BIGSERIAL PRIMARY KEY,
    authority varchar(50) not null
);

create table auth.user(
    user_id BIGSERIAL PRIMARY KEY NOT NULL,
    email varchar(255) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    create_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_DATE,
    modify_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_DATE,
    failed_login_attempt INTEGER,
    is_locked BOOLEAN,
    authority_id int8 REFERENCES authorities(authority_id)
);

create table auth.oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);

create table auth.oauth_approvals (
    code VARCHAR(50) PRIMARY KEY NOT NULL,
    active boolean,
    token bytea,
    userId VARCHAR(256),
    clientId VARCHAR(256),
    scope VARCHAR(256),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

insert into auth.authorities values (1, 'CUSTOMER');
insert into auth.authorities values (2, 'BARBER');
insert into auth.authorities values (3, 'SUPERUSER');
