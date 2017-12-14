/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Fernando
 */
@Entity
public class Pedido implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String campanha;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(name = "pedidos_itens",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<ItemPedido> itens;

    public Pedido(String campanha, Cliente cliente, Set<ItemPedido> itens) {
        this.campanha = campanha;
        this.cliente = cliente;
        this.itens = itens;
    }

    public Pedido() {
        cliente = new Cliente();
        itens = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCampanha() {
        return campanha;
    }

    public void setCampanha(String campanha) {
        this.campanha = campanha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public void addItens(Set<ItemPedido> itens) {
        if (this.getItens() == null) {
            this.setItens(itens);
        } else {
            for (ItemPedido iten : itens) {
                this.getItens().add(iten);
            }
        }
    }

    public double calcularPreco() {
        double preco = 0;
        if (this.getItens() != null) {
            for (ItemPedido iten : this.getItens()) {
                preco += iten.calcularPreco();
            }
        }
        return preco;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", campanha=" + campanha + ", cliente=" + cliente + ", itens=" + itens;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.id;
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
        final Pedido other = (Pedido) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
