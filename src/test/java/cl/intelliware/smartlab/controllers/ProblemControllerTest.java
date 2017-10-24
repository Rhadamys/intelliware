package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.controllers.ProblemController;
import cl.intelliware.smartlab.models.Problem;

import java.util.List;
import static java.util.Collections.singletonList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.mockito.Mockito;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@WebMvcTest(ProblemController.class)
public class ProblemControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProblemController problemController;

    @Test
    public void testGetAllProblems() throws Exception
    {
        Problem problem = new Problem();
        problem.setProblem_id(1);

        List<Problem> allProblems = singletonList(problem);

        given(problemController.getAllProblems()).willReturn(allProblems);

        mockMvc.perform(get("/problems/all")
                .with(user("user").password("password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(problem.getProblem_id())));
    }

    @Test
    public void testFindOne() throws Exception
    {
        Problem problem = new Problem();
        problem.setProblem_id(1);

        given(problemController.getProblem((int)problem.getProblem_id()))
                .willReturn(problem);

        mockMvc.perform(get("/problems/1")
                .with(user("user").password("password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(problem.getProblem_id())));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
