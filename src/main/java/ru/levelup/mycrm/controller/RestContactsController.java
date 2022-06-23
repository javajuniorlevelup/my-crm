package ru.levelup.mycrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.levelup.mycrm.model.dto.ContactDto;
import ru.levelup.mycrm.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/data/contacts")
@RequiredArgsConstructor
public class RestContactsController {

    private final ContactService contactService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContactDto> index(@RequestParam(value = "q", required = false) String query) {
        if (query == null) {
            return contactService.findAll();
        }
        return contactService.findByQuery(query);
    }

    @PutMapping
    public String update(ContactDto dto)
    {
        return null;
    }

}
