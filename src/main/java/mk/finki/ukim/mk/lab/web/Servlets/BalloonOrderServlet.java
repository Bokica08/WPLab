package mk.finki.ukim.mk.lab.web.Servlets;

import mk.finki.ukim.mk.lab.Exceptions.UserNotFound;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "BalloonOrderServlet", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;
    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine, OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        String color = (String) req.getSession().getAttribute("color");
        String size = (String) req.getSession().getAttribute("size");
        context.setVariable("color",color);
        context.setVariable("size",size);
        resp.setContentType("application/xhtml+xml");
        springTemplateEngine.process("deliveryInfo.html", context, resp.getWriter());
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String color = (String) req.getSession().getAttribute("golemina");
        String size = (String) req.getSession().getAttribute("boja");
        String username = (String) req.getSession().getAttribute("username");
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("dateForOrder"));
        localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        req.getSession().setAttribute("time",localDateTime);

        Order order=null;
        try {
            order = orderService.placeOrder(color,size,username, localDateTime);
        } catch (UserNotFound e) {
            throw new RuntimeException(e);
        }
        req.getSession().setAttribute("order",order);
        resp.sendRedirect("/ConfirmationInfo");
    }
}
