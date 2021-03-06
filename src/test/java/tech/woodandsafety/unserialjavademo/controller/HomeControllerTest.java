package tech.woodandsafety.unserialjavademo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testIndex() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void testAbout() throws Exception {
        mvc.perform(get("/about")).andExpect(status().isOk());
    }

    @Test
    void testContact() throws Exception {
        mvc.perform(get("/contact")).andExpect(status().isOk());
    }
}