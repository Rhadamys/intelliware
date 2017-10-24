package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Role;
import cl.intelliware.smartlab.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/roles")
public class RoleController
{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Role> getAllRoles()
    {
        return roleRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Role getRole(@PathVariable("id") Integer id)
    {
        System.out.println(roleRepository);
        long lid = id.longValue();
        return roleRepository.findOne(lid);
    }
}
