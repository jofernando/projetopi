/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidades;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author fernando
 */
public class ItemPedidoTest {

    public ItemPedidoTest() {
    }

    @Test
    public void deveCalcularPreco() {
        assertEquals(22.2, new ItemPedido(2, new Produto("12121-1", "amelia", 11.1)).calcularPreco(), 0.00001);
    }

}
