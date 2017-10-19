package cl.intelliware.smartlab.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Submissions")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "submission_id")
    private long submission_id;

    @NotNull
    private String code;

    @NotNull
    private Date submittedAt;

    @NotNull
    private int failTest;

    @NotNull
    private int succededTest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @NotNull
    private boolean compilationError;

    public long getSubmission_id() {
        return submission_id;
    }

    public void setSubmission_id(long submission_id) {
        this.submission_id = submission_id;
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

    public void setFailTest(int failTest) {
        this.failTest = failTest;
    }

    public int getSuccededTest() {
        return succededTest;
    }

    public void setSuccededTest(int succededTest) {
        this.succededTest = succededTest;
    }

    public boolean isCompilationError() {
        return compilationError;
    }

    public void setCompilationError(boolean compilationError) {
        this.compilationError = compilationError;
    }
}
