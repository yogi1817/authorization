package com.spj.authorization.security.pojo;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class GoogleOAuth2UserInfo implements Serializable {
    private String id;
    private String name;
    private String email;
    private String imageUrl;
}
