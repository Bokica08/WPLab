package mk.finki.ukim.mk.lab.web.Servlets;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BalloonListServlet", urlPatterns = "/servlet")
public class BalloonListServlet extends HttpServlet{

    private final BalloonService balloonService;
    private final SpringTemplateEngine springTemplateEngine;

    public BalloonListServlet(BalloonService balloonService, SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        List<Balloon> balloonList = balloonService.listAll();
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("balloons", balloonList);
        resp.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("listBalloons.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String color = req.getParameter("color");
        req.getSession().setAttribute("boja",color);
        resp.sendRedirect("/selectBalloon");
    }
}
