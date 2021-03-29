package cz.cvut.kotyna.onlineedu.jsf;

import cz.cvut.kotyna.onlineedu.service.AvatarService;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Named
@ApplicationScoped
public class ImageStreamer {

    @EJB
    private AvatarService avatarService;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
            byte[] image = avatarService.findAvatar(Integer.valueOf(imageId)).getBlob();
            return null;
        }
    }

}