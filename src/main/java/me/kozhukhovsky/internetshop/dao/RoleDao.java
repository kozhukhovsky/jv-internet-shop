package me.kozhukhovsky.internetshop.dao;

import me.kozhukhovsky.internetshop.model.Role;

public interface RoleDao {
    Role create(Role role);

    Role getById(Long id);

    Role getByName(String name);
}
