package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.Exceptions.UserNotFound;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String viewOrders(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        String username = (String) request.getSession().getAttribute("username");
        List<Order> orders;
        try {
            orders = orderService.searchByClient(username);
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("orders",orders);
        return "AllOrders";
    }

    @GetMapping("/filtered")
    public String viewFilteredOrders(HttpServletRequest request,Model model, @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                                     @RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo)
    {


        User user=(User) request.getSession().getAttribute("user");
        List<Order> orders=orderService.findByDateCreatedBetweenAndUser(dateFrom,dateTo,user);
        model.addAttribute("orders",orders);
        return "FilterOrders";
    }
}
