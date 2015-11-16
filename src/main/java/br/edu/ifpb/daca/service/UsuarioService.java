package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.UsuarioDao;
import br.edu.ifpb.daca.entities.Usuario;
import br.edu.ifpb.daca.validation.DacaException;
import br.edu.ifpb.daca.validation.DacaPersistenceException;
import br.edu.ifpb.daca.validation.DacaServiceException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

public class UsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioDao usuarioDao;

    /**
     * Salva um novo usuario.
     *
     * @param usuario.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public void save(Usuario usuario) throws DacaServiceException {
        try {
            usuarioDao.save(usuario);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Atualiza os dados de um usuario.
     *
     * @param usuario.
     * @return Usuario.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public Usuario update(Usuario usuario) throws DacaServiceException {
        try {
            return usuarioDao.update(usuario);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Exclui um usuario.
     *
     * @param usuario.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.REQUIRED)
    public void delete(Usuario usuario) throws DacaServiceException {
        try {
            usuarioDao.delete(usuario);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Retorna um usuario cadastrado pelo id;
     *
     * @param id
     * @return Usuario.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public Usuario getById(Long id) throws DacaServiceException {
        try {
            return usuarioDao.getById(id);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Recupera todos os tamanhos cadastrados.
     *
     * @return List of Usuario
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public List<Usuario> getAll() throws DacaServiceException {
        try {
            return usuarioDao.getAll();
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    /**
     * Retorna um usuario cadastrado pelo login;
     *
     * @param login
     * @return Usuario.
     * @throws br.edu.ifpb.daca.validation.DacaServiceException
     */
    @Transactional(rollbackOn = {DacaException.class}, value = Transactional.TxType.SUPPORTS)
    public Usuario getByUserName(String login) throws DacaServiceException {
        try {
            return usuarioDao.getByUserName(login);
        } catch (DacaPersistenceException ex) {
            throw new DacaServiceException(ex.getMessage(), ex);
        }
    }

    public void criptografarSenha(Usuario usuario) throws DacaServiceException {
        usuario.setSenha(criptografarSenha(usuario.getSenha()));
    }

    public boolean comparePassword(String oldPass, String newPass) 
            throws DacaServiceException {
        if(oldPass.equals(criptografarSenha(newPass))) {
            return true;
        }
        return false;
    }
    
    /**
     * Método que criptografa uma dada senha usando o método hash SHA-256.
     *
     * @param password senha a ser criptografada
     * @return senha criptografada
     * @throws DacaServiceException lançada caso ocorra algum erro durante o
     * processo
     */
    private static String criptografarSenha(String password) throws DacaServiceException {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);
            return output;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new DacaServiceException("Não foi possível criptografar a senha!");
        }
    }
}
