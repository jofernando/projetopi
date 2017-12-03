/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.interfaces.PedidoDAO;
import model.entidades.Pedido;
import util.JpaUtil;

/**
 *
 * @author Fernando
 */
public class BancoDeDadosPedidoDAO implements PedidoDAO {

    private EntityManagerFactory emf;

    public BancoDeDadosPedidoDAO(String pu) {
	this.emf = JpaUtil.getEntityManagerFactory(pu);
    }

    public BancoDeDadosPedidoDAO() {
	this.emf = JpaUtil.getEntityManagerFactory();
    }

    @Override
    public boolean estaCadastrado(Pedido t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    List<Pedido> pedidos = manager.createQuery("from Pedido p where p.cliente.id = :cliente_id"
		    + " and p.campanha = :campanha", Pedido.class)
		    .setParameter("campanha", t.getCampanha())
		    .setParameter("cliente_id", t.getCliente().getId())
		    .getResultList();
	    if (t.getId() != 0 && pedidos.size() == 1 && pedidos.get(0).getId() == (t.getId())) {
		return false;
	    }
	    return !pedidos.isEmpty();
	} catch (Exception e) {
	    return false;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean inserir(Pedido t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    manager.getTransaction().begin();
	    manager.persist(t);
	    manager.getTransaction().commit();
	    return true;
	} catch (Exception e) {
	    return false;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean alterar(Pedido t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    manager.getTransaction().begin();
	    manager.merge(t);
	    manager.getTransaction().commit();
	    return true;
	} catch (Exception e) {
	    return false;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean deletar(Pedido t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    manager.getTransaction().begin();
	    manager.remove(manager.merge(t));
	    manager.getTransaction().commit();
	    return true;
	} catch (Exception e) {
	    return false;
	} finally {
	    manager.close();
	}
    }

    @Override
    public Pedido buscar(int id) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return (Pedido) manager.find(Pedido.class, id);
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

    @Override
    public List<Pedido> buscarTodos() {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return manager.createQuery("from Pedido p", Pedido.class).getResultList();
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

}
