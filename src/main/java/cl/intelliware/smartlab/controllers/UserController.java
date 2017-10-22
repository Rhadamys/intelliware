package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.User;
import cl.intelliware.smartlab.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController
{
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUser()
    {
        return userRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody User getTestCase(@PathVariable("id") Integer id)
    {
        System.out.println(userRepository);
        long lid = id.longValue();
        return userRepository.findOne(lid);
    }
}
