package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Role;
import cl.intelliware.smartlab.models.User;

import cl.intelliware.smartlab.repositories.RoleRepository;
import cl.intelliware.smartlab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/students")
public class StudentController
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final Role studentRole;

    @Autowired
    public StudentController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

        studentRole = roleRepository.findByName("Student");
    }


    @GetMapping(path="/")
    public @ResponseBody Iterable<User> getAllStudents()
    {
        return userRepository.findUsersByRolesContains(studentRole);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody User getStudent(@PathVariable("id") Integer id)
    {
        long lid = id.longValue();
        User user = userRepository.findOne(lid);
        if (user.getRoles().contains(studentRole)){
            return user;
        }
        return null;
    }
}
