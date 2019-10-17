package me.kozhukhovsky.internetshop.service;

import me.kozhukhovsky.internetshop.model.Role;

public interface RoleService {
    Role create(Role role);

    Role getById(Long id);

    Role getByName(String name);
}
