package ru.levelup.mycrm.model.dto;

import lombok.Data;
import ru.levelup.mycrm.validation.CompanyNotEmpty;

import javax.validation.constraints.NotBlank;

@Data
@CompanyNotEmpty
public class ContactDto {

    private Long id;

    @NotBlank
    private String firstname;

    private String lastname;

    private String middlename;

    @NotBlank
    private String phone;

    private String email;

    private Long companyId;

    private String companyName;

}
