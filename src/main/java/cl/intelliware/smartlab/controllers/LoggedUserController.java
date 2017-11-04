package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.LoggedUser;
import cl.intelliware.smartlab.models.Role;
import cl.intelliware.smartlab.models.User;
import cl.intelliware.smartlab.repositories.UserRepository;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/loggedUsers")
public class LoggedUserController
{
    private final UserRepository userRepository;
    LoggedUser loggedUser = LoggedUser.getInstance();

    @Autowired
    public LoggedUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method =  RequestMethod.GET)
    public Principal getUserDetails(OAuth2Authentication authentication, Principal principal) throws IOException {
        loggedUser.getInstance().setUserDetails(authentication);
        return principal;
    }


    @RequestMapping(value = "/fullName", method = RequestMethod.GET)
    public Object getUserEmail() throws IOException, JSONException {
        //loggedUser.getInstance().setUserDetails(authentication);

        JSONObject json = new JSONObject();

        //System.out.println(json);

        String fullName;
        if(loggedUser.getDetails()==null)
            fullName = "Invitado";
        else
            fullName = loggedUser.getFirstName() + " " + loggedUser.getLastName();

        json.put("name", fullName);

        return json.toString();
    }

    @RequestMapping(path="/new", method= RequestMethod.GET)
    public void createStudent()
    {
        if(loggedUser.getInstance().getFlag() == 0) {
            User user = userRepository.findByMail(loggedUser.getInstance().geteMail());
            //System.out.println("el usuario es: " + user);
            //System.out.println("el correo actual es: " + loggedUser.getInstance().geteMail());
            if ((user == null) && (loggedUser.getInstance().geteMail() != null)) {
                //System.out.println("LOOK AT ME");
                loggedUser.getInstance().setFlag(1);

                User neo = new User();
                //Set<Role> roles = new HashSet<Role>();
                //Role rol = new Role();
                //rol.setName("Unassigned");
                //rol.setId(4L);
                //roles.add(rol);
                //neo.setRut("1");
                neo.setFirstName(loggedUser.getInstance().getFirstName());
                neo.setLastName(loggedUser.getInstance().getLastName());
                neo.setMail(loggedUser.getInstance().geteMail());
                //neo.setRoles(roles);
                userRepository.save(neo);
            }
            else if((user != null)&&(loggedUser.getInstance().geteMail() != null))
                loggedUser.getInstance().setFlag(1);
        }
        else
            //System.out.println("No es necesario pasar por la BD denuevo");
        return;
    }



    /*@RequestMapping(value = "/role", method = RequestMethod.GET)
    public String getUserRole(OAuth2Authentication authentication) throws IOException
    {
        LoggedUser.getInstance().setUserDetails(authentication);
        LoggedUser.getInstance().defineRole(determineRol());
        return LoggedUser.getInstance().getRole();
    }

    //NO request mapping method
    private String determineRol()
    {
        return "unassigned";
    }
    */
}
