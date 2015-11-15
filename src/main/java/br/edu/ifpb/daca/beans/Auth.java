package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Usuario;
import br.edu.ifpb.daca.service.UsuarioService;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Auth extends AbstractBean {

    private Usuario usuario;
    private String userName;

    @Inject
    UsuarioService usuarioService;

    public Usuario getUsuario() {
        if (usuario == null) {
            try {
                return usuarioService.getByUserName(recoversUserName());
            } catch (DacaServiceException ex) {
                errorMessageReport(ex.getMessage());
            }
        }
        usuario = new Usuario();
        usuario.setNome("Default");
        return usuario;
    }

    public String getUserName() {
        return recoversUserName();
    }

    private String recoversUserName() {
        Principal principal = FacesContext.getCurrentInstance()
                .getExternalContext().getUserPrincipal();
        if (principal != null) {
            return principal.getName();
        }
        return "";
    }
}
