package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Problem;
import cl.intelliware.smartlab.repositories.ProblemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/problems")
public class ProblemController
{
    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemController(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Problem> getAllProblems()
    {
        return problemRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Problem getProblem(@PathVariable("id") Integer id)
    {
        System.out.println(problemRepository);
        long lid = id.longValue();
        return problemRepository.findOne(lid);
    }
}
