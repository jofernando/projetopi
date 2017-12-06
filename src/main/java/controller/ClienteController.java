/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.ClienteModel;
import model.entidades.Cliente;
import util.FacesUtil;

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
        try {
            if (model.inserir(cliente)) {
                FacesUtil.adicionarMensagemAviso(null, "Cliente cadastrado", null);
            }
        } catch (Exception ex) {
            FacesUtil.adicionarMensagemErro(null, ex.getMessage(), null);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void alterarAction() {
        try {
            if (model.alterar(cliente)) {
                FacesUtil.adicionarMensagemAviso(null, "Cliente alterado", null);
            }
        } catch (Exception ex) {
            FacesUtil.adicionarMensagemErro(null, ex.getMessage(), null);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarAction() {
        try {
            if (model.deletar(cliente)) {
                FacesUtil.adicionarMensagemAviso(null, "Cliente deletado", null);
            }
        } catch (Exception ex) {
            FacesUtil.adicionarMensagemErro(null, ex.getMessage(), null);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarAction(Cliente cliente) {
        try {
            if (model.deletar(cliente)) {
                FacesUtil.adicionarMensagemAviso(null, "Cliente deletado", null);
            }
        } catch (Exception ex) {
            FacesUtil.adicionarMensagemErro(null, ex.getMessage(), null);
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
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
