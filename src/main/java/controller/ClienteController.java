/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.ClienteModel;
import model.entidades.Cliente;

/**
 *
 * @author Fernando
 */
@ManagedBean
@ViewScoped
public class ClienteController {

    private ClienteModel model = null;
    private Cliente cliente;
    private boolean estadoBusca;

    public ClienteController() {
	model = new ClienteModel(ClienteModel.BANCODADOS);
	cliente = new Cliente();
	estadoBusca = true;
    }

    public void inserirAction() {
	if (model.inserir(cliente)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente cadastrado", null));
	}
    }

    public void alterarAction() {
	if (model.alterar(cliente)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente alterado", null));
	}
    }

    public void deletarAction() {
	if (model.deletar(cliente)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente deletado", null));
	}
    }

    public void deletarAction(Cliente cliente) {
	if (model.deletar(cliente)) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente deletado", null));
	}
    }

    public Cliente buscarAction(int id) {
	return model.buscar(id);
    }

    public List<Cliente> buscarTodosAction() {
	return model.buscarTodos();
    }

    public Cliente buscarPorCpfAction(String cpf) {
	return model.buscarPorCpf(cpf);
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public boolean isEstadoBusca() {
	return estadoBusca;
    }

    public void setEstadoBusca(boolean estadoBusca) {
	this.estadoBusca = estadoBusca;
    }

    public void limpar() {
	cliente = new Cliente();
    }

    public void preparaBuscar() {
	limpar();
	estadoBusca = true;
    }

    public void preparaCadastrar() {
	limpar();
	estadoBusca = false;
    }

    public void preparaAlterar(Cliente cliente) {
	this.cliente = cliente;
	estadoBusca = false;
    }

    public void preparaSalvar() {
	if (cliente.getId() == 0) {
	    inserirAction();
	} else {
	    alterarAction();
	}
    }
}
