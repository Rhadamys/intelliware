package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Teacher;
import cl.intelliware.smartlab.repositories.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/teachers")
public class TeacherController
{
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Teacher> getAllTeachers()
    {
        return teacherRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Teacher getTeacher(@PathVariable("id") Integer id)
    {
        System.out.println(teacherRepository);
        long lid = id.longValue();
        return teacherRepository.findOne(lid);
    }
}
