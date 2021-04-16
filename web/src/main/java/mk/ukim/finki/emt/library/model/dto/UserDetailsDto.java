package mk.ukim.finki.emt.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.emt.library.model.User;
import mk.ukim.finki.emt.library.model.enumerations.Role;

@Data
@AllArgsConstructor
public class UserDetailsDto {
    private String username;
    private Role role;


    public static UserDetailsDto of(User user) {
       return new UserDetailsDto(user.getUsername(), user.getRole());
    }
}
