package cl.intelliware.smartlab.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "problem_id")
    private long problemId;

    @NotNull
    private String title;

    @NotNull
    private String statement;

    @JsonFormat(pattern = "dd/MM/yyyy' a las 'hh:mm")
    @NotNull
    private Date publishedAt;

    @NotNull
    private Date deadline;

    @NotNull
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    @JsonIgnore
    private User teacher;

    @OneToMany(
            mappedBy = "problem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TestCase> testCases;

    @OneToMany(
            mappedBy = "problem",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<Assignment> assignments;

    @PrePersist
    void onCreate() {
        this.publishedAt = new Date();
        this.updatedAt = this.publishedAt;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = new Date();
    }

    public Problem() {}

    public Problem(String title, String statement, Date deadline, User teacher ){
        this.title = title;
        this.statement = statement;
        this.deadline = deadline;
        this.assignments = new HashSet<>();
        this.testCases = new HashSet<>();
        this.teacher = teacher;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public Set<TestCase> getTestCases() {
        return testCases;
    }

    public void addTestCase(TestCase testCase) {
        this.testCases.add(testCase);
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public long getProblemId() {
        return problemId;
    }

    public void setProblemId(long problem_id) {
        this.problemId = problem_id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
