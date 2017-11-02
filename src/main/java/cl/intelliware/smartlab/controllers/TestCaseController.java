package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.TestCase;
import cl.intelliware.smartlab.repositories.TestCaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/testCases")
public class TestCaseController
{
    private final TestCaseRepository testCaseRepository;

    @Autowired
    public TestCaseController(TestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<TestCase> getAllTestCases()
    {
        return testCaseRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody TestCase getTestCase(@PathVariable("id") Integer id)
    {
        System.out.println(testCaseRepository);
        long lid = id.longValue();
        return testCaseRepository.findOne(lid);
    }
}
