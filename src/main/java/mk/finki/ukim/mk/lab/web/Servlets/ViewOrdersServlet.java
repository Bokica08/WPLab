package mk.finki.ukim.mk.lab.web.Servlets;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ViewOrdersServlet", urlPatterns = "/allOrders")
public class ViewOrdersServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final OrderService orderService;

    public ViewOrdersServlet(SpringTemplateEngine springTemplateEngine,OrderService orderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.orderService=orderService;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context=new WebContext(request,response,request.getServletContext());
        context.setVariable("orders",this.orderService.listAll());
        this.springTemplateEngine.process("AllOrders",context,response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
