package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Section;
import cl.intelliware.smartlab.repositories.SectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/sections")
public class SectionController
{
    private final SectionRepository sectionRepository;

    @Autowired
    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Section> getAllSections()
    {
        return sectionRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Section getSection(@PathVariable("id") Integer id)
    {
        System.out.println(sectionRepository);
        long lid = id.longValue();
        return sectionRepository.findOne(lid);
    }
}
