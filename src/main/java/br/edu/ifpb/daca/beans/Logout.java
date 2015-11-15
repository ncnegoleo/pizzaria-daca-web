package br.edu.ifpb.daca.beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class Logout extends AbstractBean implements Serializable{

    private static final long serialVersionUID = 1L;

    public String efetuarLogout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "index.xhtml?faces-redirect=true";
    }
}
