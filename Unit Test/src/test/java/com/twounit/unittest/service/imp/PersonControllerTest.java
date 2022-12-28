package com.twounit.unittest.service.imp;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;

import com.twounit.unittest.controller.PersonController;
import com.twounit.unittest.dto.PersonDto;
import com.twounit.unittest.service.PersonServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonServices personServices;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        // given
        PersonDto person = PersonDto.builder().name("Berkay").lastName("Eraydın").build();

        // when
        // perform, Api'ye Http çağrısı yap
        ResultActions actions = mockMvc.perform(post("/person")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(person)));

        // then
        // KisiDto.class ı için bir tane captor oluştur. anlık state'i yakalamak için
        ArgumentCaptor<PersonDto> captor = ArgumentCaptor.forClass(PersonDto.class);
        // .save 'in 1 kere çağrıldığından emin ol kontrolü.
        // O methoda gideni de captor yakalayacak.
        verify(personServices, times(1)).save(captor.capture());

        assertThat(captor.getValue().getName()).isEqualTo("Berkay");
        assertThat(captor.getValue().getLastName()).isEqualTo("Eraydın");
        // Bu actions sonrasında bana OK gelmeli.
        actions.andExpect(status().isOk());
    }

    @Test
    void whenValidInput_thenReturns400() throws Exception {
        // given

        // when
        ResultActions actions = mockMvc.perform(post("/person")
                .contentType(CONTENT_TYPE)
                .content(objectMapper.writeValueAsString("test-value")));

        // then
        actions.andExpect(status().isBadRequest());
    }


    @Test
    void whenCallTumunuListele_thenReturns200() throws Exception {
        // given
        PersonDto person = PersonDto.builder().name("Berkay").lastName("Eraydin").build();
        when(personServices.getAll()).thenReturn(Arrays.asList(person));

        // when
        MvcResult mvcResult = mockMvc.perform(get("/person")
                .accept(CONTENT_TYPE)).andReturn();


        // then
        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(personServices, times(1)).getAll();
        assertThat(objectMapper.writeValueAsString(Arrays.asList(person)))
                .isEqualToIgnoringWhitespace(responseBody);
    }

    @Test
    void whenCallTumunuListele_thenReturnsNoData() throws Exception {
        // given
        when(personServices.getAll()).thenReturn(Collections.emptyList());

        // when
        MvcResult mvcResult = mockMvc.perform(get("/person")
                .accept(CONTENT_TYPE)).andReturn();

        // then
        String responseBody = mvcResult.getResponse().getContentAsString();
        verify(personServices, times(1)).getAll();
        assertThat(objectMapper.writeValueAsString(Collections.emptyList()))
                .isEqualToIgnoringWhitespace(responseBody);
    }
}