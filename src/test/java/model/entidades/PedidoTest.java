/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidades;

import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fernando
 */
public class PedidoTest {

    public PedidoTest() {
    }

    public Pedido pedido1() {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        cliente.setId(1);
        Produto produto = new Produto("1111-1", "primeiro", 11.1);
        produto.setId(1);
        ItemPedido item = new ItemPedido(2, produto);
        item.setId(1);
        pedido.setCampanha("a1");
        pedido.setCliente(cliente);
        Set<ItemPedido> itens = new HashSet<>();
        itens.add(item);
        pedido.setItens(itens);
        pedido.setId(1);
        return pedido;
    }

    public Pedido pedido2() {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente("Maria", "maria@gmail.com", "(22)22222-2222", "Rua segunda", "222.222.222-22");
        cliente.setId(2);
        Produto produto = new Produto("2222-2", "segunda", 222.2);
        produto.setId(2);
        ItemPedido item = new ItemPedido(4, produto);
        item.setId(2);
        pedido.setCampanha("b2");
        pedido.setCliente(cliente);
        Set<ItemPedido> itens = new HashSet<>();
        itens.add(item);
        pedido.setItens(itens);
        pedido.setId(2);
        return pedido;
    }

    public Pedido novoPedido() {
        Cliente cliente = new Cliente("Maria", "maria@gmail.com", "(22)22222-2222", "Rua segunda", "222.222.222-22");
        cliente.setId(2);
        Produto produto = new Produto("2222-2", "segunda", 222.2);
        produto.setId(2);
        ItemPedido item = new ItemPedido(4, produto);
        Set<ItemPedido> itens = new HashSet<>();
        itens.add(item);

        Pedido pedido = new Pedido("c3", cliente, itens);
        return pedido;
    }

    @Test
    public void deveCalcularPreco() {
        assertEquals(22.2, pedido1().calcularPreco(), 0.00001);
    }

    @Test
    public void deveCalcularPercoZero() {
        assertEquals(0, new Pedido().calcularPreco(), 0.00001);
    }

    @Test
    public void naoDeveAdicionarItem() {
        Pedido pedido = pedido1();
        Produto produto = new Produto("1111-1", "primeiro", 11.1);
        produto.setId(1);
        ItemPedido item = new ItemPedido(2, produto);
        item.setId(1);
        pedido.addItem(item);

        assertEquals(1, pedido.getItens().size());
    }

}
