package cl.intelliware.smartlab.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="Sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "section_id")
    private long id;

    @NotNull
    private String semester;

    @ManyToMany(mappedBy = "sectionsAttending")
    @JsonIgnore
    private Set<User> students;

    @ManyToMany(mappedBy = "sectionsTeaching")
    @JsonIgnore
    private Set<User> teachers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public Set<User> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<User> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", semester='" + semester + '\'' +
                ", students=" + students +
                ", teachers=" + teachers +
                '}';
    }
}
