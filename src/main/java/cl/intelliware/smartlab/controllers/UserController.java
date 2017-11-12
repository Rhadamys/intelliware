package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.User;
import cl.intelliware.smartlab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path="/users")
public class UserController
{
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody User getUser(@PathVariable("id") Integer id)
    {
        long lid = id.longValue();
        return userRepository.findOne(lid);
    }

    @RequestMapping("/logged")
	public Principal loggedUserInfo(Principal principal) {
		return principal;
	}

}
