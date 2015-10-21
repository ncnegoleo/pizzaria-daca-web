package br.edu.ifpb.daca.entities;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.service.ClienteService;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
public class ClienteConverter implements Converter {

    ClienteService clienteService = new ClienteService(new ClienteDao());
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.trim().isEmpty()) {
            return null;
        }
        long id = Long.parseLong(value);
        
        return clienteService.getById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
                    return null;
            }
            Long id = ((Cliente) value).getId();
            
            return (id != null) ? id.toString() : null;
    }


    
}
