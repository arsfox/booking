package app.servlet;

import app.facade.settings.GetMenu;
import app.repository.model.Menu;
import app.repository.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name="MainServlet", urlPatterns = "")
public class MainServlet extends HttpServlet {

    GetMenu menu;

    @Override
    public void init() throws ServletException {
        super.init();
        menu = new GetMenu();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        List<Menu> menuItems = menu.fromJson()
                .stream()
                .filter(c -> c.getRoleId().getId().equals(user.getRole().getId()))
                .collect(Collectors.toList());

        resp.setContentType("text/html");
        req.setAttribute("menu", menuItems);
        req.setAttribute("user", user);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

}
