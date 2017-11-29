package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Section;
import cl.intelliware.smartlab.models.User;
import cl.intelliware.smartlab.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        long lid = id.longValue();
        return sectionRepository.findOne(lid);
    }

    @GetMapping(path="/{id}/summary")
    public @ResponseBody Object[] getSummary(@PathVariable("id") Integer id)
    {
        long lid = id.longValue();
        Section section =  sectionRepository.findOne(lid);

        return section.getStudents().stream().map(User::getAssignmentSummary).toArray();
    }
}
