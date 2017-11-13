package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.utils.PyInterpreter.PyInterpreter;
import cl.intelliware.smartlab.models.Submission;
import cl.intelliware.smartlab.repositories.SubmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/submissions")
public class SubmissionController
{
    private final SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionController(SubmissionRepository submissionRepository) {
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
    public @ResponseBody Void postSubmission(@RequestBody Submission submission){
        PyInterpreter interpreter = new PyInterpreter(submission.getCode());


        System.out.println(submission.toString());


        interpreter.run("Hola mundo!");

        return null;
    }
}
