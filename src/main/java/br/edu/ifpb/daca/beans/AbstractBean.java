package br.edu.ifpb.daca.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    protected void errorMessageReport(String details) {
        reportMessage(true, details);
    }
    
    protected void successMessageReport(String details) {
        reportMessage(false, details);
    }
    
    protected void reportMessage(boolean isError, String details) {
        String type = "Sucesso!";
        Severity severity = FacesMessage.SEVERITY_INFO;
        
        if(isError) {
            type = "Erro!";
            severity = FacesMessage.SEVERITY_ERROR;
            FacesContext.getCurrentInstance().validationFailed();
        }
        
        FacesMessage msg = new FacesMessage(severity, type, details);
        
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
