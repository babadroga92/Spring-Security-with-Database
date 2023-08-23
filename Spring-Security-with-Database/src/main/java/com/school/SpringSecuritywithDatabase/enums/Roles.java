package com.school.SpringSecuritywithDatabase.enums;

import org.springframework.context.support.BeanDefinitionDsl;

import javax.management.relation.Role;


public enum Roles {
    STUDENT("student"),
    PROFESSOR("professor"),
    ADMIN("admin");

    private String message;

    Roles(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public static Roles fromString(String role) {
        for (Roles role1 : Roles.values()) {
            if (role1.message.equalsIgnoreCase(role)) {
                return role1;
            }
        }
        throw new IllegalArgumentException("No enum constant for status: " + role);
    }
}
