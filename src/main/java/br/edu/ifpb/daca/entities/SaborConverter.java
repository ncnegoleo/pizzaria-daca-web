package br.edu.ifpb.daca.entities;

import br.edu.ifpb.daca.service.SaborService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SaborConverter implements Converter {
    
    @Inject
    SaborService SaborService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        long id = Long.parseLong(value);

        try {
            return SaborService.getById(id);
        } catch (DacaServiceException ex) {
            String msgErroStr = String.format(
                    "Erro de convers�o! N�o foi poss�vel realizar a convers�o da string '%s' para o tipo esperado.",
                    value);
            FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
            throw new ConverterException(msgErro);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Long id = ((Sabor) value).getId();

        return (id != null) ? id.toString() : null;
    }
}
