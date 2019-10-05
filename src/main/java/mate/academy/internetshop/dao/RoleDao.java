package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Role;

public interface RoleDao {
    Role create(Role role);

    Role getById(Long id);

    Role getByName(String name);
}
