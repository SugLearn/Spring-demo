package com.example.angularspringdemo.security;

public enum ApplicationUserPermission {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    ACCESS_READ("access:read"),
    ACCESS_WRITE("access:write");

    private final String permission;

    ApplicationUserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
