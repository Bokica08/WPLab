package mk.finki.ukim.mk.lab.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User is not found");
    }
}
