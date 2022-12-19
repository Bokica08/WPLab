package mk.finki.ukim.mk.lab;

import mk.finki.ukim.mk.lab.Exceptions.UserNotFound;
import mk.finki.ukim.mk.lab.Exceptions.WrongCredentialsException;
import mk.finki.ukim.mk.lab.model.Converter.UserFullnameConverter;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullname;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaUserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import mk.finki.ukim.mk.lab.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginTest {
    @Mock
    private JpaUserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
        String name="Bojan, T";
        User user=new User("Bojan","bureksomeso");
        Mockito.when(this.userRepository.findByUsernameAndPassword(Mockito.matches("Bojan"), Mockito.matches("bureksomeso"))).thenReturn(Optional.of(user));
        this.userService = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }
    @Test
    public void testSuccessLogin() {
        User user=this.userService.login("Bojan","bureksomeso");

        Mockito.verify(this.userService).login("Bojan","bureksomeso");


        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("password do not mach", "bureksomeso", user.getPassword());
        Assert.assertEquals("username do not mach", "Bojan", user.getUsername());
    }
    @Test
    public void testNullUsername() {
        Assert.assertThrows("UserNotFound expected",
                UserNotFound.class,
                () -> this.userService.login(null, "bureksomeso"));
        Mockito.verify(this.userService).login(null,"bureksomeso");
    }
    @Test
    public void testNullPassword() {
        Assert.assertThrows("UserNotFound expected",
                UserNotFound.class,
                () -> this.userService.login("Bojan", null));
        Mockito.verify(this.userService).login("Bojan",null);
    }
    @Test
    public void testWrongUsername() {
        String us="";
        Assert.assertThrows("UserNotFound expected",
                UserNotFound.class,
                () -> this.userService.login(us, "bureksomeso"));
        Mockito.verify(this.userService).login(us,"bureksomeso");
    }
    @Test
    public void testWrongPassword() {
        String pw="";
        Assert.assertThrows("UserNotFound expected",
                UserNotFound.class,
                () -> this.userService.login("Bojan", pw));
        Mockito.verify(this.userService).login("Bojan",pw);
    }
    @Test
    public void testBothNull() {
        Assert.assertThrows("UserNotFound expected",
                UserNotFound.class,
                () -> this.userService.login(null,null));
        Mockito.verify(this.userService).login(null,null);
    }
}
