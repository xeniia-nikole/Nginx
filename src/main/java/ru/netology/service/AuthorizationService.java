package ru.netology.service;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.netology.component.Authorities;
import ru.netology.exception.InvalidCredentials;
import ru.netology.exception.UnauthorizedUser;
import ru.netology.repository.UserRepository;

import java.util.List;

public class AuthorizationService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AuthorizationService.class);
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            log.error("User name or password is empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "User name or password is empty",
                    new InvalidCredentials("User name or password is empty"));
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            log.error("Unknown user " + user);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Unknown user " + user,
                    new UnauthorizedUser("Unknown user " + user));
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}