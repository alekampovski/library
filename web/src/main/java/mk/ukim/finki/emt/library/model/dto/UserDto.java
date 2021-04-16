package mk.ukim.finki.emt.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.emt.library.model.enumerations.Role;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String repeatPassword;
    private String name;
    private String surname;
    private Role role;
}
