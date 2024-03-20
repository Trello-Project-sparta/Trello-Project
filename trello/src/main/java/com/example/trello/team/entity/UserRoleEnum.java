package com.example.trello.team.entity;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    HOST(Authority.HOST),  // 사용자 권한
    GUEST(Authority.GUEST);  // 관리자 권한

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {

        public static final String HOST = "ROLE_HOST";
        public static final String GUEST = "ROLE_GUEST";
    }

}
