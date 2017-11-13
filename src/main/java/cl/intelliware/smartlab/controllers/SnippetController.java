package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.models.Assignment;
import cl.intelliware.smartlab.models.Snippet;
import cl.intelliware.smartlab.models.User;
import cl.intelliware.smartlab.repositories.AssignmentRepository;
import cl.intelliware.smartlab.repositories.SnippetRepository;
import cl.intelliware.smartlab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping(path="/snippets")
public class SnippetController
{
    private final SnippetRepository snippetRepository;
    private final UserRepository userRepository;

    @Autowired
    public SnippetController(SnippetRepository snippetRepository, UserRepository userRepository) {
        this.snippetRepository = snippetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(path="/")
    public @ResponseBody
    Iterable<Snippet> getAllSnippets()
    {
        return snippetRepository.findAllByOrderByIdDesc();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Snippet getSnippet(@PathVariable("id") Integer id)
    {
        long lid = id.longValue();
        return snippetRepository.findOne(lid);
    }

    @PostMapping(path = "/")
    public @ResponseBody Snippet postSnippet(@RequestBody SnippetRequest snippetRequest)
    {
        User user = userRepository.findOne(snippetRequest.getUser_id());

        Snippet snippet = new Snippet();

        snippet.setUser(user);
        snippet.setTitle(snippetRequest.getTitle());
        snippet.setCode(snippetRequest.getCode());
        snippet.setDescription(snippetRequest.getDescription());

        snippetRepository.save(snippet);
        return snippet;
    }
}

class SnippetRequest {
    private Long user_id;
    private String title;
    private String description;
    private String code;

    SnippetRequest(){ }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SnippetRequest{" +
                "user_id=" + user_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}