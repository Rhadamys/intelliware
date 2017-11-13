package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Assignment;
import cl.intelliware.smartlab.repositories.AssignmentRepository;

import cl.intelliware.smartlab.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/assignments")
public class AssignmentController
{
    private final AssignmentRepository assignmentRepository;
    private final ProblemRepository problemRepository;

    @Autowired
    public AssignmentController(AssignmentRepository assignmentRepository,ProblemRepository problemRepository) {
        this.assignmentRepository = assignmentRepository;
        this.problemRepository = problemRepository;
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Assignment> getAllAssignments()
    {
        return assignmentRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Assignment getAssignment(@PathVariable("id") Integer id)
    {
        long lid = id.longValue();
        return assignmentRepository.findOne(lid);
    }
}
