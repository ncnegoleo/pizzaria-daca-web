package br.edu.ifpb.daca.validation.validators;

import br.edu.ifpb.daca.validation.AttributeValidator;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("telefoneValidator")
public class TelefoneValidator implements Validator {

    private final String telefoneRegex = "\\([0-9]{2}\\)[0-9]{4,5}-[0-9]{4}";

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!AttributeValidator.stringRegexValidator(value.toString(), telefoneRegex)) {
            FacesMessage msg = new FacesMessage("Erro de validação de telefone.",
					"Formato esperado: (99)9999-9999 ou (99)99999-9999");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
        }
    }

}
