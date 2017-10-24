package cl.intelliware.smartlab.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TestCases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "testCase_id")
    private long testCaseId;

    @NotNull
    private String input;

    @NotNull
    private String output;

    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(long testCase_id) {
        this.testCaseId = testCase_id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
