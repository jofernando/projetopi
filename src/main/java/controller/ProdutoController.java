/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    private boolean estadoBusca;

    public ProdutoController() {
	model = new ProdutoModel(ProdutoModel.BANCODADOS);
	produto = new Produto();
	estadoBusca = true;
    }

    public void inserirAction() {
	model.inserir(produto);
    }

    public void alterarAction() {
	model.alterar(produto);
    }

    public void deletarAction() {
	model.deletar(produto);
    }

    public void deletarAction(Produto produto) {
	model.deletar(produto);
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

    public boolean isEstadoBusca() {
	return estadoBusca;
    }

    public void setEstadoBusca(boolean estadoBusca) {
	this.estadoBusca = estadoBusca;
    }

    public void preparaBuscar() {
	limpar();
	estadoBusca = true;
    }

    public void preparaCadastrar() {
	limpar();
	estadoBusca = false;
    }

    public void preparaAlterar(Produto produto) {
	this.produto = produto;
	estadoBusca = false;
    }

    public void preparaSalvar() {
	if (produto.getId() == 0) {
	    inserirAction();
	} else {
	    alterarAction();
	}
    }
}
