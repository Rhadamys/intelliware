package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.LoggedUser;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/loggedUsers")
public class LoggedUserController
{

    LoggedUser loggedUser = LoggedUser.getInstance();

    @RequestMapping(method =  RequestMethod.GET)
    public Object getUserDetails(OAuth2Authentication authentication) throws IOException {
        loggedUser.getInstance().setUserDetails(authentication);
        return LoggedUser.getInstance().getDetails();
    }


    @RequestMapping(value = "/fullName", method = RequestMethod.GET)
    public Object getUserEmail(OAuth2Authentication authentication) throws IOException {
        loggedUser.getInstance().setUserDetails(authentication);
        String first = LoggedUser.getInstance().getFirstName();
        String last = LoggedUser.getInstance().getLastName();
        String fullName = first + " " + last;
        System.out.println(fullName);
        JSONObject json = new JSONObject();
        try{
            json.put("name",fullName);
        }

         catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
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
