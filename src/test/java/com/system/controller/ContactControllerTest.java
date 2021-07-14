package com.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.domain.dto.ContactDTO;
import com.system.domain.models.Contact;
import com.system.domain.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ContactControllerTest {
    @MockBean
    ContactService contactService;

    @Autowired
    private MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenListOfAllContacts_shouldReturnTheListWith200() throws Exception {
        Contact contact = new Contact();
        contact.setName("test");
        contact.setPhone("12345-6789");
        contact.setEmail("test@example.com");

        when(contactService.findByUser()).thenReturn(Collections.singletonList(contact));
        mvc.perform(get(("/contacts")))
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void findContactByName_shouldReturnTheContactWith200() throws Exception {
        Contact contact = new Contact();
        contact.setName("jhon");
        contact.setPhone("12345-6789");
        contact.setEmail("jhon@example.com");
        when(contactService.update(any(ContactDTO.class))).thenReturn(contact);
        mvc.perform(put("/contacts")
                .content(mapper.writeValueAsString(contact))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
