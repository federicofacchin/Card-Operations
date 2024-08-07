package com.federico.facchin.ejercicio1y2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.federico.facchin.ejercicio1y2.models.Entities.Card;
import com.federico.facchin.ejercicio1y2.models.Entities.View.CardDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static com.federico.facchin.ejercicio1y2.testUtils.TestEntityFactory.getAmexCard;
import static com.federico.facchin.ejercicio1y2.testUtils.TestEntityFactory.getCardDto;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:16.3");

    @DynamicPropertySource
    public static void overrideDBProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",container::getJdbcUrl);
        registry.add("spring.datasource.password",container::getPassword);
        registry.add("spring.datasource.username",container::getUsername);

    }
    private Card card;
    private CardDto cardDto;

    @BeforeEach
    private void init(){
        card = getAmexCard();
        cardDto = getCardDto();
    }


    @Test
    void createCard() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/card")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").exists());
    }
    @Test
    void getCardInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/card/1234567890123456")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists());
    }




    @Test
    void matchCards() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/card/matchCards/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(card)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists());;
    }
}
