package br.edu.ifpb.daca.service;

import br.edu.ifpb.daca.dao.PedidoDao;
import br.edu.ifpb.daca.entities.Pedido;
import br.edu.ifpb.daca.entities.PedidoDelivery;
import br.edu.ifpb.daca.entities.PedidoLocal;

public class PedidoService {

    PedidoDao pedidoDao;

    public PedidoService() {
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public void saveDelivery(PedidoDelivery pedido) {

        pedidoDao.save(pedido);
    }

    public void saveLocal(PedidoLocal pedido) {

        pedidoDao.save(pedido);
    }

    public PedidoDelivery updateDelivery(PedidoDelivery pedido) {
        return (PedidoDelivery) pedidoDao.update(pedido);
    }

    public PedidoLocal updateLocal(PedidoDelivery pedido) {
        return (PedidoLocal) pedidoDao.update(pedido);
    }

    public void delete(Pedido pedido) {
        pedidoDao.delete(pedido);
    }

    public Pedido getById(Long id) {
        return pedidoDao.getById(id);
    }

    public PedidoDelivery getDeliveryById(Long id) {
        return (PedidoDelivery) pedidoDao.getById(id);
    }

    public PedidoLocal getLocalById(Long id) {
        return (PedidoLocal) pedidoDao.getById(id);
    }

    // GetAll
    public void closeConn() {
        pedidoDao.close();
    }
}
