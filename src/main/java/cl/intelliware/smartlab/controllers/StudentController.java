package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Student;
import cl.intelliware.smartlab.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/students")
public class StudentController
{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Student getStudent(@PathVariable("id") Integer id)
    {
        System.out.println(studentRepository);
        long lid = id.longValue();
        return studentRepository.findOne(lid);
    }
}
