/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.dao.interfaces.ProdutoDAO;
import model.entidades.Produto;
import util.JpaUtil;

/**
 *
 * @author Fernando
 */
public class BancoDeDadosProdutoDAO implements ProdutoDAO {

    private EntityManagerFactory emf;

    public BancoDeDadosProdutoDAO(String pu) {
	this.emf = JpaUtil.getEntityManagerFactory(pu);
    }

    public BancoDeDadosProdutoDAO() {
	this.emf = JpaUtil.getEntityManagerFactory();
    }

    @Override
    public Produto buscarPorCodigo(String codigo) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return (Produto) manager.createQuery("from Produto p where p.codigo = :codigo")
		    .setParameter("codigo", codigo)
		    .getSingleResult();
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean estaCadastrado(Produto t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    List<Produto> produtos = manager.createQuery("from Produto p where p.codigo = :codigo", Produto.class)
		    .setParameter("codigo", t.getCodigo())
		    .getResultList();
	    if (t.getId() != 0 && produtos.size() == 1 && produtos.get(0).getId() == t.getId()) {
		return false;
	    }
	    return !produtos.isEmpty();
	} catch (Exception e) {
	    return false;
	} finally {
	    manager.close();
	}
    }

    @Override
    public boolean inserir(Produto t) {
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
    public boolean alterar(Produto t) {
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
    public boolean deletar(Produto t) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    manager.getTransaction().begin();
	    manager.createQuery("delete from ItemPedido i where i.produto.id = :produto_id")
		    .setParameter("produto_id", t.getId())
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
    public Produto buscar(int id) {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return manager.find(Produto.class, id);
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }

    @Override
    public List<Produto> buscarTodos() {
	EntityManager manager = this.emf.createEntityManager();
	try {
	    return manager.createQuery("from Produto p", Produto.class).getResultList();
	} catch (Exception e) {
	    return null;
	} finally {
	    manager.close();
	}
    }
}
