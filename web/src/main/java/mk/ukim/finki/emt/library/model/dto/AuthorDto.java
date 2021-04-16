package mk.ukim.finki.emt.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDto {
    private String name;
    private String surname;
    private Long country;
}
