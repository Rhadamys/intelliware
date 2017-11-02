package cl.intelliware.smartlab.models;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import java.io.IOException;
import java.security.Principal;

public class LoggedUser
{
    private static LoggedUser instance = new LoggedUser();

    private String role = "unassigned";
    private String eMail;
    private Object details;
    private String firstName;
    private String lastName;

    //SINGLETON
    public static LoggedUser getInstance()
    {
        if(instance == null)
        {
            instance = new LoggedUser();
        }
        return instance;
    }

    private LoggedUser()
    {
    }

    public void setUserDetails(OAuth2Authentication authentication) throws IOException
    {
        System.out.println(authentication.getUserAuthentication().getDetails());
        if(this.details == null)
        {
            this.details = authentication.getUserAuthentication().getDetails();

        }
        if(this.eMail == null)
        {
            String detailsString = LoggedUser.getInstance().getDetails().toString();

            String detailsParts[] = detailsString.split(",");

            String emailDetail = detailsParts[1];
            String emailParts[] = emailDetail.split("=");
            String email = emailParts[1];

            this.eMail = email;
        }
        if(this.firstName == null)
        {
            String detailsString = LoggedUser.getInstance().getDetails().toString();

            String detailsParts[] = detailsString.split(",");

            String firstNameDetails = detailsParts[3];
            String firstNameDetails2[] = firstNameDetails.split("=");
            String firstNameDetails3[] = firstNameDetails2[1].split(" ");
            String f = firstNameDetails3[0];

            this.eMail = f;
        }
        if(this.lastName == null)
        {
            String detailsString = LoggedUser.getInstance().getDetails().toString();

            String detailsParts[] = detailsString.split(",");

            String lastNameDetails = detailsParts[3];
            String lastNameDetails2[] = lastNameDetails.split("=");
            String lastNameDetails3[] = lastNameDetails2[1].split(" ");
            String l = lastNameDetails3[0];

            this.eMail = l;
        }
    }

    public void defineRole(String rol)
    {
        if(this.role == null)
        {
            this.role = rol;
        }
    }


    public String geteMail()
    {
        return eMail;
    }

    public Object getDetails()
    {
        return details;
    }

    public String getRole()
    {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
