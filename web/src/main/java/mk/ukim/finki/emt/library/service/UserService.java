package mk.ukim.finki.emt.library.service;

import mk.ukim.finki.emt.library.model.User;
import mk.ukim.finki.emt.library.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> register(UserDto userDto);
}
