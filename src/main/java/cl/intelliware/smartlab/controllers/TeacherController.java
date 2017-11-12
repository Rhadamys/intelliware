package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Role;
import cl.intelliware.smartlab.models.User;
import cl.intelliware.smartlab.repositories.RoleRepository;

import cl.intelliware.smartlab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/teachers")
public class TeacherController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final Role role;

    @Autowired
    public TeacherController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        role = roleRepository.findByName("Teacher");
    }


    @GetMapping(path = "/")
    public @ResponseBody
    Iterable<User> getAllTeachers() {
        return userRepository.findUsersByRolesContains(role);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    User getStudent(@PathVariable("id") Integer id) {
        long lid = id.longValue();
        User user = userRepository.findOne(lid);
        if (user.getRoles().contains(role)) {
            return user;
        }
        return null;
    }
}

