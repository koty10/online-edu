/*package cz.cvut.kotyna.onlineedu.filters;

import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.service.LoginService;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/student/*")
public class StudentFilter implements Filter {

    @EJB
    private LoginService service;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String remoteUser = request.getRemoteUser();

        if (remoteUser != null) {
            HttpSession session = request.getSession();

            if (session.getAttribute("student") == null) {
                Student student = service.getLoggedInStudent();
                session.setAttribute("student", student);
            }
        }

        chain.doFilter(req, res);

    }
}
*/
