package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Sabor;
import br.edu.ifpb.daca.service.SaborService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SaborBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Sabor> sabores;

    @Inject
    private SaborService saborService;

    @PostConstruct
    public void init() {
        setSaborList();
    }

    public List<Sabor> getSabores() {
        return sabores;
    }

    private void setSaborList() {
        try {
            sabores = saborService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }
}
