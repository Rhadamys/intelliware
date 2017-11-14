package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.TestCase;
import cl.intelliware.smartlab.repositories.AssignmentRepository;
import cl.intelliware.smartlab.utils.PyInterpreter.PyInterpreter;
import cl.intelliware.smartlab.models.Submission;
import cl.intelliware.smartlab.repositories.SubmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/submissions")
public class SubmissionController
{
    private final AssignmentRepository assignmentRepository;
    private final SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionController(AssignmentRepository assignmentRepository,
                                SubmissionRepository submissionRepository) {
        this.assignmentRepository = assignmentRepository;
        this.submissionRepository = submissionRepository;
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Submission> getAllSubmissions()
    {
        return submissionRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Submission getSubmission(@PathVariable("id") Integer id)
    {
        System.out.println(submissionRepository);
        long lid = id.longValue();
        return submissionRepository.findOne(lid);
    }

    @PostMapping(path = "/")
    public @ResponseBody Submission postSubmission(@RequestBody Submission submission){
        PyInterpreter interpreter = new PyInterpreter(submission.getCode());
        for(TestCase testCase: submission.getAssignment().getProblem().getTestCases()) {
            String out = interpreter.run(testCase.getInput());
            if(out.equals(testCase.getOutput()))
                submission.addSuccededTest();
            else
                submission.addFailTest();
        }
        assignmentRepository.delete(submission.getAssignment().getId());
        submission.setAssignment(null);
        return submissionRepository.save(submission);
    }
}
