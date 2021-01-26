package hello.client.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmotionalRestController.class)
@ActiveProfiles("test")
@WithMockUser(value = "sdmin")
public class EmotionalRestControllerIntegrationControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void pushHappyEventTest() throws Exception {
        mvc.perform(get("/eventHappy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void pushSadEventTest() throws Exception {
        mvc.perform(get("/eventSad")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
