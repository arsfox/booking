package app.filter;

import app.repository.model.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter  implements Filter
{
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter (ServletRequest request, ServletResponse response,
                          FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null) {
            chain.doFilter(request, response);
        } else {
            if(!req.getRequestURI().contains("check")){
                ServletContext ctx = filterConfig.getServletContext();
                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

//        if(user == null) {
//            Cookie[] cookies = req.getCookies();
//            if (cookies != null) {
//                for (Cookie ck : cookies) {
//                    if ("password".equals(ck.getName())) {
//                        String password = ck.getValue();
//                    }
//                }
////                resp.sendRedirect(req.getContextPath() + "/login.jsp");
//            }
////            resp.sendRedirect(req.getContextPath() + "/login.jsp");
//        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }


}
