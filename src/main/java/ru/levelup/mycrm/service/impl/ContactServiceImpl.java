package ru.levelup.mycrm.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.mycrm.model.Company;
import ru.levelup.mycrm.model.Contact;
import ru.levelup.mycrm.model.User;
import ru.levelup.mycrm.model.dto.ContactDto;
import ru.levelup.mycrm.repo.CompaniesRepo;
import ru.levelup.mycrm.repo.ContactRepo;
import ru.levelup.mycrm.repo.UsersRepo;
import ru.levelup.mycrm.service.ContactService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepo contactRepo;
    private final CompaniesRepo companiesRepo;

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
    public List<ContactDto> findByQuery(String query) {
        return contactRepo.findByQuery(query).stream().map(entity -> map(entity)).collect(Collectors.toList());
    }

    @Override
    @Transactional
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

            if (contactDto.getCompanyId() != -1) {

            } else {
                Company company = new Company();
                company.setName(contactDto.getCompanyName());
                company.setCreatedAt(new Date());
                contact.setCompany(company);
            }

            contact = contactRepo.save(contact);
            return contact.getId();
        }).orElseThrow(() -> new UsernameNotFoundException(email));

        return id;
    }

    private ContactDto map(Contact contact) {
        return modelMapper.map(contact, ContactDto.class);
    }
}
