/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.interfaces.UsuarioDAO;
import model.entidades.Usuario;
import util.JpaUtil;

/**
 *
 * @author Fernando
 */
public class BancoDeDadosUsuarioDAO implements UsuarioDAO {

    private EntityManagerFactory emf;

    public BancoDeDadosUsuarioDAO(String pu) {
	this.emf = JpaUtil.getEntityManagerFactory(pu);
    }

    public BancoDeDadosUsuarioDAO() {
	this.emf = JpaUtil.getEntityManagerFactory();
    }

    @Override
    public Usuario login(String username, String password) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return (Usuario) manager.createQuery("from Usuario u where u.username = :username"
		    + " and u.password = :password")
		    .setParameter("username", username)
		    .setParameter("password", password)
		    .getSingleResult();
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean estaCadastrado(Usuario t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    List<Usuario> usuarios = manager.createQuery("from Usuario u where u.username = :username")
		    .setParameter("username", t.getUsername())
		    .getResultList();
	    if (t.getId() != 0 && usuarios.size() == 1 && usuarios.get(0).getId() == (t.getId())) {
		return false;
	    }
	    return !usuarios.isEmpty();
	} catch (Exception e) {
	    return false;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean inserir(Usuario t) {
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
    public boolean alterar(Usuario t) {
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
    public boolean deletar(Usuario t) {
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
    public Usuario buscar(int id) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return (Usuario) manager.find(Usuario.class, id);
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

    @Override
    public List<Usuario> buscarTodos() {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return manager.createQuery("from Usuario u").getResultList();
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

}
