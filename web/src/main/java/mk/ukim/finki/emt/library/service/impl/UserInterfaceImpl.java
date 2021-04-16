package mk.ukim.finki.emt.library.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.library.model.User;
import mk.ukim.finki.emt.library.model.dto.UserDto;
import mk.ukim.finki.emt.library.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.emt.library.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.emt.library.repository.UserRepository;
import mk.ukim.finki.emt.library.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInterfaceImpl implements UserService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> register(UserDto userDto) {
        if(userRepository.findById(userDto.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException();
        if(!userDto.getPassword().equals(userDto.getRepeatPassword()))
            throw new PasswordsDoNotMatchException();

        User user = new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),
                userDto.getName(), userDto.getSurname(), userDto.getRole());
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
