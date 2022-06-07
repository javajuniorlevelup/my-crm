package ru.levelup.mycrm.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.levelup.mycrm.model.Contact;
import ru.levelup.mycrm.model.User;
import ru.levelup.mycrm.model.dto.ContactDto;
import ru.levelup.mycrm.repo.ContactRepo;
import ru.levelup.mycrm.repo.UsersRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepo contactRepo;
    private final ModelMapper modelMapper;
    private final UsersRepo usersRepo;

    @Override
    public Optional<ContactDto> findById(Long id) {
        return contactRepo.findById(id).map(this::map);
    }

    @Override
    public List<ContactDto> findAll() {
        return Streamable.of(contactRepo.findAll()).map(entity -> map(entity)).toList();
    }

    @Override
    public Long save(ContactDto contactDto) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails)auth.getPrincipal()).getUsername();
        Optional<User> currentUser = usersRepo.findByEmail(email);

        long id = currentUser.map(user -> {
            Contact contact = new Contact();
            contact.setFirstName(contactDto.getFirstname());
            contact.setLastName(contactDto.getLastname());
            contact.setMiddleName(contactDto.getMiddlename());
            contact.setPhone(contactDto.getPhone());
            contact.setEmail(contactDto.getEmail());
            contact.setCreatedBy(user);

            contact = contactRepo.save(contact);
            return contact.getId();
        }).orElseThrow(() -> new UsernameNotFoundException(email));

        return id;
    }

    private ContactDto map(Contact contact) {
        return modelMapper.map(contact, ContactDto.class);
    }
}
