/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
     * , pedido
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "quant_produto")
    private int quantProduto = 1;
    @ManyToOne
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(int quantProduto, Produto produto) {
        this.quantProduto = quantProduto;
        this.produto = produto;
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

    public double calcularPreco() {
        return quantProduto * produto.getPreco();
    }

    @Override
    public String toString() {
        return "ItemPedido{" + "id=" + id + ", quantProduto=" + quantProduto + ", produto=" + produto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.produto);
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
        return true;
    }

}
