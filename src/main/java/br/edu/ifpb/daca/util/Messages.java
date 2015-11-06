package br.edu.ifpb.daca.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class Messages {

    public static void addFlashMessage(FacesMessage facesMessage) {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
