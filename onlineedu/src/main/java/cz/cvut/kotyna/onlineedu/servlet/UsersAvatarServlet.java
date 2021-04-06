package cz.cvut.kotyna.onlineedu.servlet;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.UsersAvatar;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/avatars/user/*")
public class UsersAvatarServlet extends HttpServlet {

    @EJB
    private UserService userService;

    // TODO if teacher and gender
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Integer userId = Integer.valueOf(request.getPathInfo().substring(1)); // Get substring after "/avatars/"
        UserAccount userAccount = userService.findUserAccount(userId);

        UsersAvatar optionalUsersAvatar = userAccount.getUsersAvatars().stream()
                .filter(studentsAvatar
                        -> studentsAvatar.getTimeTo().isAfter(LocalDateTime.now()))
                .filter(UsersAvatar::isActive)
                .findFirst().orElse(null);

        if (optionalUsersAvatar == null) {
            if (userAccount.getRole().equals("teacher")) {
                if (userAccount.getGender().equals("male")) {
                    response.sendRedirect("/resources/images/teacher-male.png");
                } else if (userAccount.getGender().equals("female")) {
                    response.sendRedirect("/resources/images/teacher-female.png");
                }
            } else if (userAccount.getRole().equals("student")) {
                if (userAccount.getGender().equals("male")) {
                    response.sendRedirect("/resources/images/student-male.png");
                } else if (userAccount.getGender().equals("female")) {
                    response.sendRedirect("/resources/images/student-female.png");
                }
            } else {
                response.sendRedirect("/resources/images/avatar-woman.png");
            }
            return;
        }

        Avatar avatar = optionalUsersAvatar.getAvatar();

        response.setHeader("Content-Type", getServletContext().getMimeType(avatar.getFileExtension()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + avatar.getName() + "\"");

        BufferedOutputStream output = null;

        try {
            output = new BufferedOutputStream(response.getOutputStream());
            output.write(avatar.getBlob());
        } finally {
            if (output != null) try { output.close(); } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}