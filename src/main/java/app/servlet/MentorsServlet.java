package app.servlet;

import app.facade.settings.GetMentors;
import app.facade.settings.GetUsers;
import app.repository.model.Mentor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="MentorsServlet", urlPatterns = "/mentors")
public class MentorsServlet extends HttpServlet {

    GetMentors mentors;

    @Override
    public void init() throws ServletException {
        super.init();
        mentors = new GetMentors();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Mentor> mentorList = mentors.fromJson();

        resp.setContentType("text/html");
        req.setAttribute("menu", mentorList);
        req.getRequestDispatcher("mentors.jsp").forward(req, resp);
    }


}
