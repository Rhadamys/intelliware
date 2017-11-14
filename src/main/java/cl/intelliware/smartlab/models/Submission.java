package cl.intelliware.smartlab.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "submission_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @NotNull
    private String code;

    @CreationTimestamp
    private Date submittedAt;

    @Column
    private int failTest = 0;

    @Column
    private int succededTest = 0;

    @Column
    private boolean compilationError = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public int getFailTest() {
        return failTest;
    }

    public void addFailTest() {
        this.failTest++;
    }

    public int getSuccededTest() {
        return succededTest;
    }

    public void addSuccededTest() {
        this.succededTest--;
    }

    public boolean isCompilationError() {
        return compilationError;
    }

    public void setCompilationError(boolean compilationError) {
        this.compilationError = compilationError;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", assignments=" + assignment +
                ", code='" + code + '\'' +
                ", submittedAt=" + submittedAt +
                ", failTest=" + failTest +
                ", succededTest=" + succededTest +
                ", compilationError=" + compilationError +
                '}';
    }
}
