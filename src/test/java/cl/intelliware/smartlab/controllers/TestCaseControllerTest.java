package cl.intelliware.smartlab.controllers;

import cl.intelliware.smartlab.controllers.TestCaseController;
import cl.intelliware.smartlab.models.TestCase;

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
@WebMvcTest(TestCaseController.class)
public class TestCaseControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestCaseController testCaseController;

    @Test
    public void testGetAllTestCases() throws Exception
    {
        TestCase testcase = new TestCase();
        testcase.setTestCaseId(1);

        List<TestCase> allTestCases = singletonList(testcase);

        given(testCaseController.getAllTestCases()).willReturn(allTestCases);

        mockMvc.perform(get("/testCases/all")
                .with(user("user").password("password"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testFindOne() throws Exception
    {
        TestCase testcase = new TestCase();
        testcase.setTestCaseId(1);

        given(testCaseController.getTestCase((int)testcase.getTestCaseId()))
                .willReturn(testcase);

        mockMvc.perform(get("/testCases/1")
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
