package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Submission;
import cl.intelliware.smartlab.repositories.SubmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/submission")
public class SubmissionController
{
    private final SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionController(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @GetMapping(path="/all")
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
}
