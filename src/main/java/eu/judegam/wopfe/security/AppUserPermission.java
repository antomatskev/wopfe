package eu.judegam.wopfe.security;

public enum AppUserPermission {
    ALL("all"),
    MANAGER("manager"),
    ADMIN("admin"),
    PRINCIPAL("principal"),
    TEACHER("teacher"),
    STUDENT("student");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
