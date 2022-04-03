package com.evolvingreality.onleave.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    private AuthoritiesConstants() {
    }

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";
    
    public static final String MANAGER = "ROLE_MANAGER";
    
    public static final String SYSTEM_ADMIN = "ROLE_SYSTEM_ADMIN";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
}
