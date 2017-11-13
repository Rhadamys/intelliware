package cl.intelliware.smartlab.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true)
    private long id;

    @Column(unique=true)
    private String rut;

    @NotNull
    private String mail;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnoreProperties("users")
    private Set<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "sections_teachers",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private Set<Section> sectionsTeaching;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "sections_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private Set<Section> sectionsAttending;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Snippet> snippets;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Problem> problems;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Assignment> assignments;

    // METHODS

    public boolean hasRole(String roleName){
        for (Role role:roles) {
            if (role.getName().equalsIgnoreCase(roleName)){
                return true;
            }
        }
        return false;
    }

    public boolean rutCheck() {
        return rut.matches("[1-9]{1,2}.[0-9]{3}.[0-9]{3}-[0-9kK]{1}");
    }

    public boolean mailCheck(){
        return mail.matches("[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$");
    }

    // GETTERS AND SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Section> getSectionsTeaching() {
        return sectionsTeaching;
    }

    public void setSectionsTeaching(Set<Section> sectionsTeaching) {
        this.sectionsTeaching = sectionsTeaching;
    }

    public Set<Section> getSectionsAttending() {
        return sectionsAttending;
    }

    public void setSectionsAttending(Set<Section> sectionsAttending) {
        this.sectionsAttending = sectionsAttending;
    }

    public List<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<Snippet> snippets) {
        this.snippets = snippets;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    /*@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", rut='" + rut + '\'' +
                ", mail='" + mail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                ", sectionsTeaching=" + sectionsTeaching +
                ", sectionsAttending=" + sectionsAttending +
                ", snippets=" + snippets +
                ", problems=" + problems +
                ", assignments=" + assignments +
                '}';
    }*/
}


