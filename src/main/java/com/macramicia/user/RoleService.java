package com.macramicia.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String name) {
        return this.roleRepository.findRoleByName(name);
    }

    public void save(Role role) {
        this.roleRepository.save(role);
    }
}
