package cl.intelliware.smartlab.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="Teachers")
public class Teacher {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teacher_id")
    private long teacherId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "teacher_section",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "section_id")
    )
    private Set<Section> sections;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @OneToMany(
            mappedBy = "teacher",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Problem> problems;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    public void setTeacherId(long teacher_id) {
        this.teacherId = teacher_id;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public long getTeacherId() {
        return teacherId;
    }

}
