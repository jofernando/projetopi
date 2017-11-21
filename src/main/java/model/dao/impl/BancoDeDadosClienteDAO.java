/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.interfaces.ClienteDAO;
import model.entidades.Cliente;
import org.hibernate.HibernateException;
import util.JpaUtil;

/**
 *
 * @author Fernando
 */
public class BancoDeDadosClienteDAO implements ClienteDAO {

    private EntityManagerFactory emf;

    public BancoDeDadosClienteDAO(String pu) {
	this.emf = JpaUtil.getEntityManagerFactory(pu);
    }

    public BancoDeDadosClienteDAO() {
	this.emf = JpaUtil.getEntityManagerFactory();
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return (Cliente) manager.createQuery("from Cliente c where c.cpf = :cpf")
		    .setParameter("cpf", cpf)
		    .getSingleResult();
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean estaCadastrado(Cliente t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    List<Cliente> clientes = manager.createQuery("from Cliente c where c.cpf = :cpf")
		    .setParameter("cpf", t.getCpf())
		    .getResultList();
	    if (t.getId() != 0 && clientes.size() == 1 && clientes.get(0).getId() == (t.getId())) {
		return false;
	    }
	    return !clientes.isEmpty();
	} catch (Exception e) {
	    return false;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean inserir(Cliente t) {
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
    public boolean alterar(Cliente t) {
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
    public boolean deletar(Cliente t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    manager.getTransaction().begin();
	    manager.createQuery("delete from Pedido p where p.cliente.id = :cliente_id")
		    .setParameter("cliente_id", t.getId())
		    .executeUpdate();
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
    public Cliente buscar(int id) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return (Cliente) manager.find(Cliente.class, id);
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

    @Override
    public List<Cliente> buscarTodos() {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return manager.createQuery("from Cliente c").getResultList();
	} catch (HibernateException e) {
	    return null;
	} finally {
	    manager.close();
	}
    }
}
