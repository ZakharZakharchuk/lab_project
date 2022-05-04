package com.example;

import com.example.controllers.TourController;
import com.example.models.Tour;
import com.example.repositories.TourRepository;
import com.example.services.tour.TourService;
import com.example.services.tour.TourServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(TourController.class)
public class ApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TourService tourService;

    @Test
    public void test() throws Exception {
        tourService = mock(TourService.class);
        when(tourService.findAll()).thenReturn(List.of(
                new Tour("USA", 300),
                new Tour("Italy", 200)));

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Italy")));
        /*this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("USA")));*/
    }
}