package br.edu.ifpb.daca.test;

import br.edu.ifpb.daca.dao.ClienteDao;
import br.edu.ifpb.daca.entities.Cliente;
import br.edu.ifpb.daca.service.ClienteService;
import br.edu.ifpb.daca.validation.exception.DifferentArgumentException;
import static br.edu.ifpb.daca.validation.msgs.ClienteExceptMsgs.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

import static org.mockito.Mockito.mock;
import static org.hamcrest.Matchers.is;

public class ClienteServiceTest {

    private Cliente cliente;
    private ClienteService clienteService;
    private ClienteDao mock;

    @Before
    public void setUp() {
        cliente = new Cliente();
        clienteService = new ClienteService();
        mock = mock(ClienteDao.class);
        clienteService.setClienteDao(mock);
    }

    /**
     * Teste que verifica se o nome não é nulo ao salvar
     */
    @Test
    public void clienteSalvoNomeNulo() {
        try {
            clienteService.save(cliente);
            fail("save() deve causar uma exceção pois a descrição do lanche é nula");
        } catch (DifferentArgumentException expected) {
            Assert.assertThat(NOME_MSG_EXCEPT.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que verifica se o nome não é nulo ao salvar
     */
    @Test
    public void clienteSalvoNomeVazio() {
        try {
            cliente.setNome("");
            clienteService.save(cliente);
            fail("save() deve causar uma exceção pois a descrição do lanche é nula");
        } catch (DifferentArgumentException expected) {
            Assert.assertThat(NOME_MSG_EXCEPT.getValor(),
                    is(expected.getMessage()));
        }
    }

}
