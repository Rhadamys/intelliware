package cl.intelliware.smartlab.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private long role_id;

    @NotNull
    private String name;


    public long getId() {return role_id;}

    public long getRole_id()
    {
        return role_id;
    }

    public void setRole_id(long role_id)
    {
        this.role_id = role_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
