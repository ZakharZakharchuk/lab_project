package com.example;

import com.example.models.Tour;
import com.example.rest.RestTourController;
import com.example.services.tour.TourService;
import com.example.services.user.UserFindService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestTourController.class)
public class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TourService tourService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserFindService userFindService;


    @Test
    public void test() throws Exception {
        List<Tour>list = List.of(
                new Tour("USA", 300),
                new Tour("Italy", 200));
        when(tourService.findAll()).thenReturn(list);

        final String expectedResponseContent = objectMapper.writeValueAsString(list);

        this.mockMvc.perform(get("/rest/tour"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent));
    }
}