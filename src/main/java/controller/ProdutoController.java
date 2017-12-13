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
import model.ProdutoModel;
import model.entidades.Produto;

/**
 *
 * @author Fernando
 */
@ManagedBean
@ViewScoped
public class ProdutoController {

    private ProdutoModel model = null;
    private Produto produto;
    private boolean busca;
    private boolean cadastro;
    private boolean alteracao;

    public ProdutoController() {
        model = new ProdutoModel(ProdutoModel.BANCODADOS);
        produto = new Produto();
        busca = true;
    }

    public void inserirAction() {
        if (model.inserir(produto)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto cadastrado", null));
        }
    }

    public void alterarAction() {
        if (model.alterar(produto)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto alterado", null));
        }
    }

    public void deletarAction() {
        if (model.deletar(produto)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto deletado", null));
        }
    }

    public void deletarAction(Produto produto) {
        if (model.deletar(produto)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto deletado", null));
        }
    }

    public Produto buscarAction(int id) {
        return model.buscar(id);
    }

    public List<Produto> buscarTodosAction() {
        return model.buscarTodos();
    }

    public Produto buscarPorCpfAction(String codigo) {
        return model.buscarPorCodigo(codigo);
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void limpar() {
        produto = new Produto();
    }

    public void preparaBuscar() {
        limpar();
        busca = true;
        cadastro = false;
        alteracao = false;
    }

    public void preparaCadastrar() {
        limpar();
        cadastro = true;
        busca = false;
        alteracao = false;
    }

    public void preparaAlterar(Produto produto) {
        this.produto = produto;
        alteracao = true;
        busca = false;
        cadastro = false;
    }

    public boolean isCadastro() {
        return cadastro;
    }

    public void setCadastro(boolean cadastro) {
        this.cadastro = cadastro;
    }

    public boolean isAlteracao() {
        return alteracao;
    }

    public void setAlteracao(boolean alteracao) {
        this.alteracao = alteracao;
    }

    public boolean isBusca() {
        return busca;
    }

    public void setBusca(boolean busca) {
        this.busca = busca;
    }
}
