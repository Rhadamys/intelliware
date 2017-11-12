package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Problem;
import cl.intelliware.smartlab.models.Assignment;
import cl.intelliware.smartlab.models.TestCase;
import cl.intelliware.smartlab.models.User;
import cl.intelliware.smartlab.repositories.AssignmentRepository;
import cl.intelliware.smartlab.repositories.ProblemRepository;
import cl.intelliware.smartlab.repositories.TestCaseRepository;
import cl.intelliware.smartlab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping(path="/problems")
public class ProblemController
{
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final TestCaseRepository testCaseRepository;
    private final AssignmentRepository assignmentRepository;

    @Autowired
    public ProblemController(UserRepository userRepository,
                             ProblemRepository problemRepository,
                             TestCaseRepository testCaseRepository,
                             AssignmentRepository assignmentRepository) {
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.testCaseRepository = testCaseRepository;
        this.assignmentRepository = assignmentRepository;
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

    @SuppressWarnings("unchecked")
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

        Problem problem = problemRepository.save(new Problem(title, statement,deadline, null));

        // Test cases
        String input, output, description;
        for(HashMap<String, Object> testCaseMap: (List<HashMap<String, Object>>) resource.get("testCases")) {
            input = testCaseMap.get("input").toString();
            output = testCaseMap.get("output").toString();
            description = testCaseMap.get("description").toString();
            TestCase testCase = testCaseRepository.save(new TestCase(input, output, description, problem));
            problem.addTestCase(testCase);
        }

        // Assignments
        User student;
        for(HashMap<String, Object> assignmentMap: (List<HashMap<String, Object>>) resource.get("assignments")) {
            student = userRepository.findByMail(assignmentMap.get("mail").toString());
            Assignment assignment = assignmentRepository.save(new Assignment(student, deadline, problem));
            problem.addAssignment(assignment);
        }
        return problem;
    }
}
