package cz.cvut.kotyna.onlineedu.servlet;

import cz.cvut.kotyna.onlineedu.entity.Avatar;
import cz.cvut.kotyna.onlineedu.service.AvatarService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;

@WebServlet("/avatars/*")
public class AvatarServlet extends HttpServlet {

    @EJB
    private AvatarService avatarService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Integer avatarId = Integer.valueOf(request.getPathInfo().substring(1)); // Get substring after "/avatars/"
        Avatar avatar = avatarService.findAvatar(avatarId);

        if (avatar == null) {
            response.sendRedirect("/resources/images/avatar-woman.png");
            return;
        }

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