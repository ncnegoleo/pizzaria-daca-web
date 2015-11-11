package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Mesa;
import br.edu.ifpb.daca.service.MesaService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class MesaBean extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<Mesa> mesas;
    
    @Inject
    private MesaService mesaService;

    @PostConstruct
    public void init() {
        setMesaList();
    }

    public List<Mesa> getClientes() {
        return mesas;
    }
    
    public void setMesaList() {
        try {
            mesas = mesaService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }
}
