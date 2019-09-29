package mate.academy.internetshop.model;

import mate.academy.internetshop.lib.IdGenerator;

public class Role {
    private final Long id;
    private RoleName roleName;

    public Role() {
        id = IdGenerator.getNextRoleId();
    }

    public Role(RoleName roleName) {
        this();
        this.roleName = roleName;
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    public Long getId() {
        return id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public enum RoleName {
        USER, ADMIN
    }
}