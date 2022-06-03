package ru.levelup.mycrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.levelup.mycrm.exc.ContactNotFoundException;
import ru.levelup.mycrm.model.dto.ContactDto;
import ru.levelup.mycrm.service.ContactService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactsController {

    private final ContactService contactService;

    @GetMapping
    public String index(Model model) {
        List<ContactDto> list = contactService.findAll();
        model.addAttribute("list", list);

        return "contacts";
    }

    @GetMapping("/new")
    public String newContact(ContactDto contactDto) {
        return "edit-contact";
    }

    @PostMapping("/new")
    public String createContact(@Valid ContactDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-contact";
        }
        long id = contactService.save(dto);
        return "redirect:/contacts/" + id;
    }

    @GetMapping("/{id}")
    public String editContact(@PathVariable("id") Long id, Model model) {
        ContactDto dto = contactService.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
        model.addAttribute("contactDto", dto);

        return "edit-contact";
    }

    @PostMapping("/{id}")
    public String updateContact() {
        return "edit-contact";
    }

}
