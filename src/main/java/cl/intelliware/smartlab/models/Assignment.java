package cl.intelliware.smartlab.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assignment_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private User student;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @CreationTimestamp
    private Date assignedAt;

    @NotNull
    private Date deadline;

    @Column
    private Float grade;

    @OneToMany(
            mappedBy = "assignment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Submission> submissions;

    public Assignment() { }

    public Assignment(User student,
                      Date deadline,
                      Problem problem) {
        this.student = student;
        this.deadline = deadline;
        this.problem = problem;
        this.submissions = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Date getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Date assignedAt) {
        this.assignedAt = assignedAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", student=" + student +
                ", problem=" + problem +
                ", assignedAt=" + assignedAt +
                ", deadline=" + deadline +
                ", grade=" + grade +
                ", submissions=" + submissions +
                '}';
    }
}
