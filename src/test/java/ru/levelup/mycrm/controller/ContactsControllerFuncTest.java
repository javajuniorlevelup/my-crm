package ru.levelup.mycrm.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Streamable;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.levelup.mycrm.model.Contact;
import ru.levelup.mycrm.repo.ContactRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ContactsControllerFuncTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepo contactRepo;

    @Container
    private static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12-alpine");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    public void shouldCreateContact() throws Exception {
        Map<String, String> requestData = Map.of(
                "firstname", "TEST_firstName",
                "lastname", "TEST_lastname",
                "phone", "TEST_phone"
        );

        // key1=value1&key2=value2...

        String data = requestData.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        mockMvc.perform(
                        post("/contacts/new")
                                .with(user("admin").password("admin").roles("ADMIN"))
                                .with(csrf())
                                .content(data)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection());

        List<Contact> allContacts = Streamable.of(contactRepo.findAll()).toList();
    }
}
