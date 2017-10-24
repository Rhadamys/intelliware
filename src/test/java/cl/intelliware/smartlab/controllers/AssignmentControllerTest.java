package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.controllers.AssignmentController;
import cl.intelliware.smartlab.models.Assignment;

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
@WebMvcTest(AssignmentController.class)
public class AssignmentControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentController assignmentController;

    @Test
    public void testGetAllAssignments() throws Exception
    {
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(1);

        List<Assignment> allAssignments = singletonList(assignment);

        given(assignmentController.getAllAssignments()).willReturn(allAssignments);

        mockMvc.perform(get("/assignments/all")
                .with(user("user").password("password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testFindOne() throws Exception
    {
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(1);

        given(assignmentController.getAssignment((int)assignment.getAssignmentId()))
                .willReturn(assignment);

        mockMvc.perform(get("/assignments/1")
                .with(user("user").password("password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
