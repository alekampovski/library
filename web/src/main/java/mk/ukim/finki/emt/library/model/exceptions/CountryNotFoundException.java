package mk.ukim.finki.emt.library.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException() {
        super("Country Not Found!");
    }
}
