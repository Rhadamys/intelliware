package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.LoggedUser;
import cl.intelliware.smartlab.models.User;
import cl.intelliware.smartlab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;

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

    @PostMapping(path="/byMail")
    public @ResponseBody User getUserByMail(@RequestBody HashMap<String, Object> request)
    {
        User user = userRepository.findByMail(request.get("email").toString());
        return user;
    }

    @RequestMapping("/logged")
	public @ResponseBody HashMap<String, Object> loggedUserInfo(OAuth2Authentication authentication) throws IOException {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("principal", authentication.getUserAuthentication().getDetails());

        LoggedUser.setUserDetails(authentication);
        User user = userRepository.findByMail(LoggedUser.getInstance().geteMail());
        userMap.put("instance", user);
        return userMap;
	}
}

