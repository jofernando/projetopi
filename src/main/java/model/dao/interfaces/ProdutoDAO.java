/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import model.entidades.Produto;

/**
 *
 * @author Fernando
 */
public interface ProdutoDAO extends DAO<Produto> {

    Produto buscarPorCodigo(String codigo);

    boolean estaCadastrado(Produto t);
}
