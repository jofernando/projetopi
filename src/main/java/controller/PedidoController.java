/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.PedidoModel;
import model.entidades.ItemPedido;
import model.entidades.Pedido;
import model.entidades.Produto;
import util.FacesUtil;

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
    private boolean busca;
    private boolean cadastro;
    private boolean alteracao;

    public PedidoController() {
        model = new PedidoModel(PedidoModel.BANCODADOS);
        pedido = new Pedido();
        busca = true;
    }

    public void inserirAction() {
        if (model.inserir(pedido)) {
            FacesUtil.adicionarMensagemAviso(null, "Pedido cadastrado", null);
        }
    }

    public void alterarAction() {
        if (model.alterar(pedido)) {
            FacesUtil.adicionarMensagemAviso(null, "Pedido alterado", null);
        }
    }

    public void deletarAction() {
        if (model.deletar(pedido)) {
            FacesUtil.adicionarMensagemAviso(null, "Pedido deletado", null);
        }
    }

    public void deletarAction(Pedido pedido) {
        if (model.deletar(pedido)) {
            FacesUtil.adicionarMensagemAviso(null, "Pedido deltado", null);
        }
    }

    public Pedido buscarAction(int id) {
        return model.buscar(id);
    }

    public List<Pedido> buscarTodosAction() {
        return model.buscarTodos();
    }

    public void limpar() {
        pedido = new Pedido();
        produtos = new ArrayList<>();
    }

    public void preparaBuscar() {
        limpar();
        busca = true;
        cadastro = false;
        alteracao = false;
    }

    public void preparaCadastrar() {
        limpar();
        busca = false;
        cadastro = true;
        alteracao = false;
    }

    public void preparaAlterar(Pedido pedido) {
        limpar();
        this.pedido = pedido;
        for (ItemPedido iten : pedido.getItens()) {
            produtos.add(iten.getProduto());
        }
        busca = false;
        cadastro = false;
        alteracao = true;
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

    public boolean isBusca() {
        return busca;
    }

    public void setBusca(boolean busca) {
        this.busca = busca;
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

    public void atualizarItens() {
        if (produtos != null) {
            for (Produto produto : produtos) {
                pedido.addItem(produto, 1);
            }
        }
    }
}
