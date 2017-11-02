package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Assignment;
import cl.intelliware.smartlab.models.Snippet;
import cl.intelliware.smartlab.repositories.AssignmentRepository;
import cl.intelliware.smartlab.repositories.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/snippets")
public class SnippetController
{
    private final SnippetRepository snippetRepository;

    @Autowired
    public SnippetController(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @GetMapping(path="/")
    public @ResponseBody
    Iterable<Snippet> getAllSnippets()
    {
        return snippetRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Snippet getSnippet(@PathVariable("id") Integer id)
    {
        long lid = id.longValue();
        return snippetRepository.findOne(lid);
    }
}