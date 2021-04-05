package cz.cvut.kotyna.onlineedu.servlet;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.entity.StudentsAvatar;
import cz.cvut.kotyna.onlineedu.entity.UserAccount;
import cz.cvut.kotyna.onlineedu.model.listDataModel.student.avatar.StudentAvatarModel;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
import cz.cvut.kotyna.onlineedu.service.UserService;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@WebServlet("/avatars/user/*")
public class UsersAvatarServlet extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Integer userId = Integer.valueOf(request.getPathInfo().substring(1)); // Get substring after "/avatars/"
        UserAccount userAccount = userService.findUserAccount(userId);

        StudentsAvatar optionalStudentsAvatar = userAccount.getStudent().getStudentsAvatars().stream()
                .filter(studentsAvatar
                        -> studentsAvatar.getTimeTo().isAfter(LocalDateTime.now()))
                .filter(StudentsAvatar::isActive)
                .findFirst().orElse(null);

        if (optionalStudentsAvatar == null) {
            response.sendRedirect("/resources/images/avatar-woman.png");
            return;
        }

        Avatar avatar = optionalStudentsAvatar.getAvatar();

        response.setHeader("Content-Type", getServletContext().getMimeType(avatar.getName()));
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