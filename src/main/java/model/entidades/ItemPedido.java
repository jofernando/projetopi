/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    private int id;
    @Column(name = "quant_produto")
    private int quantProduto = 1;
    @ManyToOne
    private Produto produto;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Pedido pedido;

    public ItemPedido() {
    }

    public ItemPedido(int quantProduto, Produto produto, Pedido pedido) {
	this.quantProduto = quantProduto;
	this.produto = produto;
	this.pedido = pedido;
    }

    public int getId() {
	return id;
    }

    public int getQuantProduto() {
	return quantProduto;
    }

    public void setQuantProduto(int quantProduto) {
	this.quantProduto = quantProduto;
    }

    public Produto getProduto() {
	return produto;
    }

    public void setProduto(Produto produto) {
	this.produto = produto;
    }

    public Pedido getPedido() {
	return pedido;
    }

    public void setPedido(Pedido pedido) {
	this.pedido = pedido;
    }

    public double calcularPreco() {
	return quantProduto * produto.getPreco();
    }

    @Override
    public String toString() {
	return "ItemPedido{" + "id=" + id + ", quantProduto=" + quantProduto + ", produto=" + produto + ", pedido=" + pedido;
    }

    @Override
    public int hashCode() {
	int hash = 3;
	hash = 23 * hash + this.id;
	hash = 23 * hash + Objects.hashCode(this.produto);
	hash = 23 * hash + Objects.hashCode(this.pedido);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final ItemPedido other = (ItemPedido) obj;
	if (this.id != other.id) {
	    return false;
	}
	if (!Objects.equals(this.produto, other.produto)) {
	    return false;
	}
	if (!Objects.equals(this.pedido, other.pedido)) {
	    return false;
	}
	return true;
    }

}
