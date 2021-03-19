package ru.otus.borodkin.elibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.borodkin.elibrary.models.LibUserDetails;
import ru.otus.borodkin.elibrary.repositories.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(s);
        if (user != null) {
            var u = new LibUserDetails(user);
            return u;
        } else {
            throw new UsernameNotFoundException(s);
        }
    }

    public UserDetails getCurrentUser() {
        return (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
