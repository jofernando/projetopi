/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.impl.BancoDeDadosPedidoDAO;
import model.dao.interfaces.DAO;
import model.dao.interfaces.PedidoDAO;
import model.entidades.Pedido;

/**
 *
 * @author Fernando
 */
public class PedidoModel {

    private DAO<Pedido> model = null;
    public static final int BANCODADOS = 1;

    public PedidoModel(int tipoArmazenamento) {
	if (tipoArmazenamento == BANCODADOS) {
	    model = new BancoDeDadosPedidoDAO();
	}
    }

    public PedidoModel(int tipoArmazenamento, String pu) {
	if (tipoArmazenamento == BANCODADOS) {
	    model = new BancoDeDadosPedidoDAO(pu);
	}
    }

    public boolean inserir(Pedido t) {
	if (t == null) {
	    throw new IllegalArgumentException("Pedido não pode ser nulo");
	} else if (t.getCliente() == null) {
	    throw new IllegalArgumentException("Cliente não pode ser nulo");
	} else if (t.getItens() == null || t.getItens().isEmpty()) {
	    throw new IllegalArgumentException("Pedido tem que ter pelo menos 1 item");
	} else if (t.getCampanha().isEmpty()) {
	    throw new IllegalArgumentException("Campanha não pode ser nulo");
	} else if (((PedidoDAO) model).estaCadastrado(t)) {
	    throw new IllegalArgumentException("Pedido com esse cliente e campanha já cadastrado");
	}
	return model.inserir(t);
    }

    public boolean alterar(Pedido t) {
	if (t == null) {
	    throw new IllegalArgumentException("Pedido não pode ser nulo");
	} else if (t.getCliente() == null) {
	    throw new IllegalArgumentException("Cliente não pode ser nulo");
	} else if (t.getItens().isEmpty() || t.getItens() == null) {
	    throw new IllegalArgumentException("Pedido tem que ter pelo menos 1 item");
	} else if (t.getCampanha() == null) {
	    throw new IllegalArgumentException("Campanha não pode ser nulo");
	} else if (((PedidoDAO) model).estaCadastrado(t)) {
	    throw new IllegalArgumentException("Pedido com esse cliente e campanha já cadastrado");
	}
	return model.alterar(t);
    }

    public boolean deletar(Pedido t) {
	if (t == null) {
	    throw new IllegalArgumentException("Selecione algum pedido para excluir");
	}
	return model.deletar(t);
    }

    public Pedido buscar(int id) {
	return model.buscar(id);
    }

    public List<Pedido> buscarTodos() {
	return model.buscarTodos();
    }
}
