package mk.finki.ukim.mk.lab.web.Servlets;

import org.springframework.security.access.prepost.PreAuthorize;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SelectBalloonServlet", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public SelectBalloonServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebContext context=new WebContext(request,response, request.getServletContext());
        response.setContentType("application/xhtml+xml");
        this.springTemplateEngine.process("selectBalloonSize.html",context,response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String size= request.getParameter("size");
        request.getSession().setAttribute("golemina",size);
        response.sendRedirect("/BalloonOrder");

    }
}
