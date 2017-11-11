package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Problem;
import cl.intelliware.smartlab.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping(path="/problems")
public class ProblemController
{
    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemController(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Problem> getAllProblems()
    {
        return problemRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Problem getProblem(@PathVariable("id") Integer id)
    {
        long lid = id.longValue();
        return problemRepository.findOne(lid);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Problem create(@RequestBody HashMap<String, Object> resource) {
        System.out.println(resource);
        return null;
    }
}
