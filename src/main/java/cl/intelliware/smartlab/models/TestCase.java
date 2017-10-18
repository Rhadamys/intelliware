package cl.intelliware.smartlab.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="TestCases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "testCase_id")
    private long testCase_id;

    @NotNull
    private String input;

    @NotNull
    private String output;

    @NotNull
    private String description;

    public long getTestCase_id() {
        return testCase_id;
    }

    public void setTestCase_id(long testCase_id) {
        this.testCase_id = testCase_id;
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
