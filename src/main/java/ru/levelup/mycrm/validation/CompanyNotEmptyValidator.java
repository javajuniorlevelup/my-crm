package ru.levelup.mycrm.validation;

import ru.levelup.mycrm.model.dto.ContactDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CompanyNotEmptyValidator implements ConstraintValidator<CompanyNotEmpty, ContactDto> {

    @Override
    public void initialize(CompanyNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ContactDto contactDto, ConstraintValidatorContext context) {

        boolean valid = contactDto.getCompanyId() != -1 || (contactDto.getCompanyName() != null && contactDto.getCompanyName().length() > 0);

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Необходимо указать компанию, либо создать новую")
                    .addPropertyNode("companyId")
                    .addConstraintViolation()

                    .buildConstraintViolationWithTemplate("Необходимо указать компанию, либо создать новую")
                    .addPropertyNode("companyName")
                    .addConstraintViolation();
        }

        return valid;
    }
}
