package ru.levelup.mycrm.controller;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import ru.levelup.mycrm.model.dto.ContactDto;
import ru.levelup.mycrm.service.ContactService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactsControllerUnitTest {

    private ContactsController subject;

    @Mock
    private ContactService contactService;

    @BeforeEach
    public void setup() {
        subject = new ContactsController(contactService);
    }

    @Test
    public void shouldCreateContact() throws Exception {
        ContactDto dto = new ContactDto();
        dto.setFirstname("TEST_firstname");
        dto.setPhone("TEST_phone");

        BindingResult bindingResult = mock(BindingResult.class);

        Mockito.when(contactService.save(any())).thenReturn(1L);

        String result = subject.createContact(dto, bindingResult);
        Assert.assertEquals("redirect:/contacts/1", result);
        Mockito.verify(contactService, times(1)).save(any());
        verifyNoMoreInteractions(contactService);
    }



}
