package br.edu.ifpb.daca.beans;

import br.edu.ifpb.daca.entities.Pizza;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class PizzaDetail extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    Pizza pizza;

    @Inject
    private Conversation conversation;

    @PostConstruct
    public void init() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public String cancel() {
        conversation.end();
        return "lanches.xhtml?faces-redirect=true";
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

}
