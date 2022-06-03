package ru.levelup.mycrm.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContactDto {

    @NotBlank
    private String firstname;

    private String lastname;

    private String middlename;

    @NotBlank
    private String phone;

    private String email;

}
