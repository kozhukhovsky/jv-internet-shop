package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Role;

public interface RoleService {
    Role create(Role role);

    Role getById(Long id);

    Role getByName(String name);
}
