package cz.cvut.kotyna.onlineedu.filters;

import cz.cvut.kotyna.onlineedu.entity.Student;
import cz.cvut.kotyna.onlineedu.service.LoginService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

//@WebFilter(urlPatterns = "/student/*", dispatcherTypes = { DispatcherType.FORWARD })
public class StudentFilter implements Filter {

    @EJB
    private LoginService loginService;

    @EJB
    private UserService userService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Student loggedInStudent = userService.getStudentByUsername(request.getUserPrincipal().getName());
        String teachingId = request.getParameter("teaching");
        String uri = request.getRequestURI();

        // No teaching id selected, redirect to default teaching
        if (teachingId == null && !uri.equals("/onlineedu/student/home.xhtml")) {
            String defaultUrl = request.getContextPath() + "/student/none.xhtml";
            response.sendRedirect(defaultUrl);
        }

        // Wrong teaching id is selected, redirect to error page
        else if (!loggedInStudent.getClassroom().getTeachingCollection().stream().map(x -> x.getId().toString()).collect(Collectors.toList()).contains(teachingId)) {
            try {
                if (!uri.equals("/onlineedu/student/wrong.xhtml"))
                {
                    String wrongUrl = request.getContextPath() + "/student/wrong.xhtml";
                    response.sendRedirect(wrongUrl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        chain.doFilter(req, res);

    }
}