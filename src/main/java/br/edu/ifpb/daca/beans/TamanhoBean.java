package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Tamanho;
import br.edu.ifpb.daca.service.TamanhoService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TamanhoBean extends AbstractBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<Tamanho> tamanhos;
    
    @Inject
    private TamanhoService tamanhoService;
    
    @PostConstruct
    public void init() {
        setTamanhoList();
    }

    public List<Tamanho> getTamanhos() {
        return tamanhos;
    }

    private void setTamanhoList() {
        try {
            tamanhos = tamanhoService.getAll();
        } catch (DacaServiceException ex) {
            errorMessageReport(ex.getMessage());
        }
    }
}
