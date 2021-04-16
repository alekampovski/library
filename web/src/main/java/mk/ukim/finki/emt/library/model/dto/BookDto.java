package mk.ukim.finki.emt.library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.emt.library.model.enumerations.Category;

@Data
@AllArgsConstructor
public class BookDto {
    private String name;
    private Category category;
    private Long author;
    private Integer availableCopies;
}
