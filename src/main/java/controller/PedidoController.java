/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.PedidoModel;
import model.entidades.ItemPedido;
import model.entidades.Pedido;
import model.entidades.Produto;

/**
 *
 * @author Fernando
 */
@ManagedBean
@ViewScoped
public class PedidoController {

    private PedidoModel model = null;
    private Pedido pedido;
    private List<Produto> produtos;
    private boolean estadoBusca;

    public PedidoController() {
	model = new PedidoModel(PedidoModel.BANCODADOS);
	pedido = new Pedido();
	estadoBusca = true;
    }

    public void inserirAction() {
	if (model.inserir(pedido)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido cadastrado", null));
	}
    }

    public void alterarAction() {
	if (model.alterar(pedido)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido alterado", null));
	}
    }

    public void deletarAction() {
	if (model.deletar(pedido)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido deletado", null));
	}
    }

    public void deletarAction(Pedido pedido) {
	if (model.deletar(pedido)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido deletado", null));
	}
    }

    public Pedido buscarAction(int id) {
	return model.buscar(id);
    }

    public List<Pedido> buscarTodosAction() {
	return model.buscarTodos();
    }

    public Pedido getPedido() {
	return pedido;
    }

    public void setPedido(Pedido pedido) {
	this.pedido = pedido;
    }

    public List<Produto> getProdutos() {
	return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
	this.produtos = produtos;
    }

    public boolean isEstadoBusca() {
	return estadoBusca;
    }

    public void setEstadoBusca(boolean estadoBusca) {
	this.estadoBusca = estadoBusca;
    }

    public void addItens() {
	Set<ItemPedido> itens = new HashSet<>();
	if (produtos != null) {
	    for (Produto produto : produtos) {
		itens.add(new ItemPedido(1, produto));
	    }
	}
	pedido.addItens(itens);
    }

    public void limpar() {
	pedido = new Pedido();
	produtos = null;
    }

    public void preparaBuscar() {
	limpar();
	estadoBusca = true;
    }

    public void preparaCadastrar() {
	limpar();
	estadoBusca = false;
    }

    public void preparaAlterar(Pedido pedido) {
	this.pedido = pedido;
	estadoBusca = false;
    }

    public void preparaSalvar() {
	if (pedido.getId() == 0) {
	    inserirAction();
	} else {
	    alterarAction();
	}
    }
}
