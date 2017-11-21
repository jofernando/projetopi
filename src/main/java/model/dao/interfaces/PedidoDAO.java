/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import model.entidades.Pedido;

/**
 *
 * @author Fernando
 */
public interface PedidoDAO extends DAO<Pedido> {

    boolean estaCadastrado(Pedido t);
}
