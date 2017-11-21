/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.interfaces;

import model.entidades.Usuario;

/**
 *
 * @author Fernando
 */
public interface UsuarioDAO extends DAO<Usuario> {

    Usuario login(String username, String password);

    boolean estaCadastrado(Usuario t);
}
