package com.logger.base.model.user;

public class UserDto {
    private Long id;
    private Long roleId;
    private String name;
    private String email;
    private String password;

    public Long getRoleId() {
        return roleId;
    }

    public UserDto() {
    }

    public UserDto(Long roleId, String name, String email, String password) {
        this.roleId = roleId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
