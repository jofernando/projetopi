/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import model.entidades.Cliente;

/**
 *
 * @author Fernando
 */
public interface ClienteDAO extends DAO<Cliente> {

    Cliente buscarPorCpf(String cpf);

    boolean estaCadastrado(Cliente t);
}
