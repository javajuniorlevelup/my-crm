package ru.levelup.mycrm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.levelup.mycrm.model.Role;
import ru.levelup.mycrm.model.User;
import ru.levelup.mycrm.repo.UsersRepo;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepo usersRepo;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //String encodedPass = encoder.encode("password");

        Optional<User> userOp = usersRepo.findByEmail(username);
        return userOp.map(user -> new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                createAuthorities(user.getRoles())
        )).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    private List<? extends GrantedAuthority> createAuthorities(List<Role> roles) {
        return ofNullable(roles)
                .map(rolesList -> rolesList.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());
    }
}
