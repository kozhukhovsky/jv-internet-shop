package me.kozhukhovsky.internetshop.service.impl;

import me.kozhukhovsky.internetshop.dao.RoleDao;
import me.kozhukhovsky.internetshop.lib.annotation.Inject;
import me.kozhukhovsky.internetshop.lib.annotation.Service;
import me.kozhukhovsky.internetshop.model.Role;
import me.kozhukhovsky.internetshop.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    @Inject
    private static RoleDao roleDao;

    @Override
    public Role create(Role role) {
        return roleDao.create(role);
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }
}
