package mk.ukim.finki.emt.library.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoMoreAvailableCopiesException extends RuntimeException{
    public NoMoreAvailableCopiesException() {
        super("No more available copies left!");
    }
}
