package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Problem;
import cl.intelliware.smartlab.models.Assignment;
import cl.intelliware.smartlab.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Problem create(@RequestBody HashMap<String, Object> resource) {
        System.out.println(resource);
        String title = resource.get("title").toString();
        String statement = resource.get("statement").toString();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        String deadlineString = resource.get("deadline").toString();
        Date deadline = null;
        try {
            deadline = format.parse(deadlineString);
        }catch(ParseException ex){
            ex.printStackTrace();
        }

        Problem newProblem = new Problem(title, statement,deadline, null);

        return problemRepository.save(newProblem);
    }
}
