package br.edu.ifpb.daca.validation.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.selectcheckboxmenu.SelectCheckboxMenu;

@FacesValidator("limitCheckboxMenuValidator")
public class LimitCheckboxMenuValidator implements Validator {

    public LimitCheckboxMenuValidator() {

    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {
        //get limit
        Integer minLimit = Integer.parseInt((String) component.getAttributes().get("minLimit"));
        Integer maxLimit = Integer.parseInt((String) component.getAttributes().get("maxLimit"));
        SelectCheckboxMenu myComponent = (SelectCheckboxMenu) component;

        if (((String[]) myComponent.getSubmittedValue()).length < minLimit ||
                ((String[]) myComponent.getSubmittedValue()).length > maxLimit) {
            FacesMessage msg
                    = new FacesMessage("Fora do limite",
                            "O limite minimo de sabores é " + minLimit + " "
                            + "e o limite maximo de sabores é " + maxLimit +"!");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
