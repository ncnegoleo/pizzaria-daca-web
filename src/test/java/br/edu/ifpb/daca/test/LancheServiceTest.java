package br.edu.ifpb.daca.test;

import br.edu.ifpb.daca.dao.LancheDao;
import br.edu.ifpb.daca.entities.Lanche;
import br.edu.ifpb.daca.service.LancheService;
import br.edu.ifpb.daca.validation.DifferentArgumentException;
import static br.edu.ifpb.daca.validation.msgs.LancheExcepMsg.*;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LancheServiceTest {

    private Lanche lanche;
    private LancheService lancheService;
    private LancheDao mock;

    @Before
    public void setUp() {
        lanche = new Lanche();
        lancheService = new LancheService();
        mock = mock(LancheDao.class);
        lancheService.setLancheDao(mock);
    }

    /**
     * Teste que verifica se a descrição não é nula ao salvar
     */
    @Test
    public void lancheSalvoDescricaoNula() {
        try {
            lancheService.save(lanche);
            fail("save() deve causar uma exceção pois a descrição do lanche é nula");
        } catch (DifferentArgumentException expected) {
            assertThat(DESCRICAO_MSG_EXCEP.getValor(),
                    is(expected.getMessage()));
        }

    }

    /**
     * Teste que verifica se a descrição do lanche não está vazia aos salvar
     */
    @Test
    public void lancheSalvoDescricaoVazia() {
        try {
            lanche.setDescricao("");
            lancheService.save(lanche);
            fail("save() deve causar uma exceção pois "
                    + "a descrição do lanche esta vazia");
        } catch (DifferentArgumentException expected) {
            assertThat(DESCRICAO_MSG_EXCEP.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que verifica se o valor unitário é negativo ao salvar
     */
    @Test
    public void lancheSalvoValorUnitarioNegativo() {
        try {
            lanche.setDescricao("Refrigerante");
            lanche.setValorUnitario(-1.0);
            lancheService.save(lanche);
            fail("save() deve causar uma exceção pois o valor unitário "
                    + "do lanche é negativo");
        } catch (DifferentArgumentException expected) {
            assertThat(VALOR_UNIT_MSG_EXCEP.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que verifica se o id não é nulo ao alterar
     */
    @Test
    public void lancheAtualizadoIdNulo() {
        try {
            lanche.setDescricao("");
            lanche.setValorUnitario(10.0);
            lancheService.update(lanche);
            fail("update() deve causar uma exceção pois o id do lanche é nulo");
        } catch (DifferentArgumentException expected) {
            assertThat(ID_MSG_EXCEPT.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que verifica se o id não é menor que 0 ao alterar
     */
    @Test
    public void lancheAtualizadoIdMenorQue1() {
        try {
            lanche.setId(0l);
            lanche.setDescricao("");
            lanche.setValorUnitario(10.0);
            lancheService.update(lanche);
            fail("update() deve causar uma exceção pois o id do lanche é nulo");
        } catch (DifferentArgumentException expected) {
            assertThat(ID_MSG_EXCEPT.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que verifica se a descrição não é nula ao alterar
     */
    @Test
    public void lancheAtualizadoDescricaoNula() {
        try {
            lanche.setId(1l);
            lancheService.update(lanche);
            fail("update() deve causar uma exceção pois "
                    + "a descrição do lanche é nula");
        } catch (DifferentArgumentException expected) {
            assertThat(DESCRICAO_MSG_EXCEP.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que verifica se a descrição do lanche não está vazia aos alterar
     */
    @Test
    public void lancheAtualizadoDescricaoVazia() {
        try {
            lanche.setId(1l);
            lanche.setDescricao("");
            lancheService.update(lanche);
            fail("update() deve causar uma exceção pois a descrição "
                    + "do lanche esta vazia");
        } catch (DifferentArgumentException expected) {
            assertThat(DESCRICAO_MSG_EXCEP.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que verifica se o valor unitário é negativo ao alterar
     */
    @Test
    public void lancheAtualizadoValorUnitarioNegativo() {
        try {
            lanche.setId(1l);
            lanche.setDescricao("Refrigerante");
            lanche.setValorUnitario(-1.0);
            lancheService.update(lanche);
            fail("update() deve causar uma exceção pois o valor unitário "
                    + "do lanche é negativo");
        } catch (DifferentArgumentException expected) {
            assertThat(VALOR_UNIT_MSG_EXCEP.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que tenta recupera Lanche alterado
     */
    @Test
    public void recuperaLancheAtualizado() {
        Long id = 1l;
        lanche.setId(id);
        lanche.setDescricao("Refrigerante");
        lanche.setValorUnitario(10.0);

        when(mock.update(lanche)).thenReturn(lanche);

        Lanche retorno = lancheService.update(lanche);

        assertThat("Lanche diferente do esperado", lanche, equalTo(retorno));
    }

    /**
     * Teste que tenta recuperar Lanche existente pelo id
     */
    @Test
    public void recuperaLanchePeloIdLancheExistente() {
        Long id = 1l;
        lanche.setId(id);
        lanche.setDescricao("Refrigerante");
        lanche.setValorUnitario(10.0);

        when(mock.getById(id)).thenReturn(lanche);

        Lanche retorno = lancheService.getById(id);

        assertThat("Lanche diferente do esperado", lanche, equalTo(retorno));
    }

    /**
     * Teste que tenta recuperar o Lanche com um id menor que 1
     */
    @Test
    public void recuperaLancheComIdMenorQue1() {
        try {
            Long id = -1l;
            lancheService.getById(id);
            fail("getById() deveria retornar uma exceção "
                    + "pois o parametro do id é negativo");
        } catch (DifferentArgumentException expected) {
            assertThat(ID_MSG_EXCEPT.getValor(),
                    is(expected.getMessage()));
        }
    }

    /**
     * Teste que tenta recuperar o Lanche Inexistente
     */
    @Test
    public void recuperaLanchePeloIdLancheInexistente() {
        Long id = 1l;

        when(mock.getById(id)).thenReturn(null);

        Lanche retorno = lancheService.getById(id);

        assertNull("O resultado de veria ser nulo", retorno);
    }
}
