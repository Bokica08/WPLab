package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.Exceptions.WrongCredentialsException;
import mk.finki.ukim.mk.lab.model.User;

public interface UserService {
    User login(String username, String password) throws WrongCredentialsException;
}
