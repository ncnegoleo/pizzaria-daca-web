package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.util.Messages;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Classe que implementa as funções básicas comuns em todos os managedbeans.
 * @author leonardo
 */
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
        
        Messages.addFlashMessage(new FacesMessage(severity, type, details));
    }
}
