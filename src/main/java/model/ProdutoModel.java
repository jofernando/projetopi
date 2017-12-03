/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.impl.BancoDeDadosProdutoDAO;
import model.dao.interfaces.DAO;
import model.dao.interfaces.ProdutoDAO;
import model.entidades.Produto;

/**
 *
 * @author Fernando
 */
public class ProdutoModel {

	private DAO<Produto> model = null;
	public static final int BANCODADOS = 1;

	public ProdutoModel(int tipoArmazenamento) {
		if (tipoArmazenamento == BANCODADOS) {
			model = new BancoDeDadosProdutoDAO();
		}
	}

	public ProdutoModel(int tipoArmazenamento, String pu) {
		if (tipoArmazenamento == BANCODADOS) {
			model = new BancoDeDadosProdutoDAO(pu);
		}
	}

	public boolean inserir(Produto t) {
		if (t == null) {
			throw new IllegalArgumentException("Produto não pode ser nulo");
		} else if (t.getCodigo().isEmpty()) {
			throw new IllegalArgumentException("Código não pode ser nulo");
		} else if (((ProdutoDAO) model).estaCadastrado(t)) {
			throw new IllegalArgumentException("Produto com este código já cadastrado");
		}
		return model.inserir(t);
	}

	public boolean alterar(Produto t) {
		if (t == null) {
			throw new IllegalArgumentException("Produto não pode ser nulo");
		} else if (t.getCodigo().isEmpty()) {
			throw new IllegalArgumentException("Código não pode ser nulo");
		} else if (((ProdutoDAO) model).estaCadastrado(t)) {
			throw new IllegalArgumentException("Produto com este código já cadastrado");
		}
		return model.alterar(t);
	}

	public boolean deletar(Produto t) {
		if (t == null) {
			throw new IllegalArgumentException("Selecione um produto para excluir");
		}
		return model.deletar(t);
	}

	public Produto buscar(int id) {
		return model.buscar(id);
	}

	public List<Produto> buscarTodos() {
		return model.buscarTodos();
	}

	public Produto buscarPorCodigo(String codigo) {
		return ((ProdutoDAO) model).buscarPorCodigo(codigo);
	}
}
