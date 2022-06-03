package ru.levelup.mycrm.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.levelup.mycrm.exc.ContactNotFoundException;

@ControllerAdvice
public class ContactsControllerAdvice {

    @ExceptionHandler(ContactNotFoundException.class)
    public String handleContactNotFound(ContactNotFoundException e, Model model) {
        model.addAttribute("id", e.getId());
        return "notFound";
    }

}
