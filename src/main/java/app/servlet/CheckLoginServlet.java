package app.servlet;

import app.facade.settings.GetUsers;
import app.repository.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="CheckLoginServlet", urlPatterns = "/check")
public class CheckLoginServlet extends HttpServlet {

    GetUsers users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = new GetUsers();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter pr = resp.getWriter();
//        pr.println("check"+req.getParameter("password")+" "+req.getParameter("email"));
        String enterEmail = req.getParameter("email");
        String enterPassword = req.getParameter("password");

        List<User> usersList = users.fromJson();

        for (User user : usersList){
            if(user.getEmail().equals(enterEmail)) {
                if(user.getPassword().equals(enterPassword)) {
                    System.out.println(user.getPassword());
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath() + "/");
                    return;
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login?showLoginError=1");
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/login?showLoginError=1");
    }


}
