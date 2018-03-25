package ru.zardi.tests;

import org.apache.catalina.core.StandardService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.zardi.tests.services.StackOverflowService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTaskMarch24ApplicationTests {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testResponse() throws Exception {
        String content = this.mockMvc
                .perform(post("/")
                                 .param("search", "java spring")
                                 .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Assert.assertTrue("The most popular question is present", content.contains("Why would I use Scala/Lift over Java/Spring?"));
    }

    @Test
    public void testStackOverflowErrors() throws Exception {
        String content = this.mockMvc
                .perform(post("/")
                                 .param("search", StackOverflowService.SIMULATE_EXCEPTION_SEARCH)
                                 .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Assert.assertTrue("Error present", content.contains("error-block"));
    }
}
