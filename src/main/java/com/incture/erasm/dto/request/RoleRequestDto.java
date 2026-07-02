package com.incture.erasm.dto.request;

import jakarta.validation.constraints.NotBlank;

public class RoleRequestDto {

    @NotBlank(message = "Role name is required")
    private String roleName;

    public RoleRequestDto() {
    }

    public RoleRequestDto(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}