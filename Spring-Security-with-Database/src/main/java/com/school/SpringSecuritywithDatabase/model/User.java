package com.school.SpringSecuritywithDatabase.model;

import com.school.SpringSecuritywithDatabase.enums.Roles;
import com.school.SpringSecuritywithDatabase.model.registration.token.ConfirmationToken;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public  class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    private boolean isEnabled = false;
    private boolean canBeDeleted = false;
    @OneToMany(mappedBy = "user",
    cascade = CascadeType.REMOVE)
    List<ConfirmationToken> tokenList;

    public User() {
    }

    public User(int id, String username, String password, String email, Roles roles, boolean isEnabled, boolean canBeDeleted) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.isEnabled = isEnabled;
        this.canBeDeleted = canBeDeleted;
    }

    public User(String username, String password, String email, Roles roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isCanBeDeleted() {
        return canBeDeleted;
    }

    public void setCanBeDeleted(boolean canBeDeleted) {
        this.canBeDeleted = canBeDeleted;
    }

    public List<ConfirmationToken> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<ConfirmationToken> tokenList) {
        this.tokenList = tokenList;
    }
}
