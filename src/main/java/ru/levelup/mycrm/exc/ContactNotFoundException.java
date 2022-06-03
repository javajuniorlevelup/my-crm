package ru.levelup.mycrm.exc;

import lombok.Value;

@Value
public class ContactNotFoundException extends RuntimeException {

    Long id;

}
