package cl.intelliware.smartlab.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TestCases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "testCase_id")
    private long id;

    @NotNull
    private String input;

    @NotNull
    private String output;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    public TestCase(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", description='" + description + '\'' +
                ", problem=" + problem +
                '}';
    }
}
