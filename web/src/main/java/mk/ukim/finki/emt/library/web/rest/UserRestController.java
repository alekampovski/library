package mk.ukim.finki.emt.library.web.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.library.model.User;
import mk.ukim.finki.emt.library.model.dto.UserDto;
import mk.ukim.finki.emt.library.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        return userService.register(userDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
