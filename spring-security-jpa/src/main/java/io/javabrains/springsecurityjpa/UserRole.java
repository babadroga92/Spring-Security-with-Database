package io.javabrains.springsecurityjpa;

import com.google.common.collect.Sets;

import java.util.Set;

public enum UserRole {
    STUDENT(Sets.newHashSet(UserPermission.STUDENT_READ)),
    ADMIN(Sets.newHashSet(UserPermission.STUDENT_READ, UserPermission.STUDENT_WRITE, UserPermission.COURSE_READ, UserPermission.COURSE_WRITE)),
    ASSISTANT(Sets.newHashSet(UserPermission.STUDENT_READ, UserPermission.COURSE_READ));

    private Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }
}
