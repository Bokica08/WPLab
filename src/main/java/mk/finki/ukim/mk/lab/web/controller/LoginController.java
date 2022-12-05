package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.Exceptions.UserNotFound;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String showLoginPage(){
        return "login";
    }
    @PostMapping
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model , HttpServletRequest request){
        User user=null;
        try{
            user=userService.login(username,password);
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("user",user);
            ShoppingCart shoppingCart = new ShoppingCart(user);
            request.getSession().setAttribute("cartId",shoppingCart.getId());
            return "redirect:/balloons";
        }
        catch(UserNotFound exception){
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";

        }
    }

}
