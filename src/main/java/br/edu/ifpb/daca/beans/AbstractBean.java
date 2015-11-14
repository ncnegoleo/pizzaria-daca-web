package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.util.Messages;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Classe que implementa as funções básicas comuns em todos os managedbeans.
 *
 * @author leonardo
 */
public class AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int ERROR_MESSAGE = 0;
    private static final int SUCCESS_MESSAGE = 1;
    private static final int WARNING_MESSAGE = 2;

    protected void errorMessageReport(String details) {
        reportMessage(ERROR_MESSAGE, details);
    }

    protected void successMessageReport(String details) {
        reportMessage(SUCCESS_MESSAGE, details);
    }

    protected void warningMessageReport(String details) {
        reportMessage(WARNING_MESSAGE, details);
    }

    protected void reportMessage(int type, String details) {
        String head = null;
        Severity severity = null;

        if (type == SUCCESS_MESSAGE) {
            head = "Sucesso!";
            severity = FacesMessage.SEVERITY_INFO;
        } else if (type == ERROR_MESSAGE) {
            head = "Erro!";
            severity = FacesMessage.SEVERITY_ERROR;
            FacesContext.getCurrentInstance().validationFailed();
        } else {
            head = "Aviso!";
            severity = FacesMessage.SEVERITY_WARN;
        }

        Messages.addFlashMessage(new FacesMessage(severity, head, details));
    }
}
