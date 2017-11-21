/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import java.util.List;

/**
 *
 * @author Fernando
 */
public interface DAO<T> {

    boolean inserir(T t);

    boolean alterar(T t);

    boolean deletar(T t);

    T buscar(int id);

    List<T> buscarTodos();
}
