package cl.intelliware.smartlab.controllers;

import java.util.List;
import static java.util.Collections.singletonList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import org.mockito.MockitoAnnotations;
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

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(TeacherController.class)
public class TeacherControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @MockBean
    private TeacherController teacherController;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    public void testGetAllTeachers() throws Exception
    {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);

        List<Teacher> allTeachers = singletonList(teacher);

        given(teacherController.getAllTeachers()).willReturn(allTeachers);

        mockMvc.perform(get("/teachers/all")
                .with(user("user").password("password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testFindOne() throws Exception
    {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(1L);

        given(teacherController.getTeacher((int)teacher.getTeacherId()))
                .willReturn(teacher);

        mockMvc.perform(get("/teachers/1")
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
