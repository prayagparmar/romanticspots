package com.bitchinc.romantics.enums;

/**
 * User: prayagparmar
 * Date: 7/17/14
 * Time: 10:29 PM
 */
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private String roleUser;

    Role(String roleUser) {
        this.roleUser = roleUser;
    }

    public String getRoleUser() {
        return roleUser;
    }
}
