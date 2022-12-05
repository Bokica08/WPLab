package mk.finki.ukim.mk.lab.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)

public class WrongCredentialsException extends RuntimeException{
    public WrongCredentialsException()
    {
        super("Nevalidni podatoci");
    }
}
