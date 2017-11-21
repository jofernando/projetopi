/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import model.UsuarioModel;
import model.entidades.Usuario;

/**
 *
 * @author Fernando
 */
@ManagedBean
@ViewScoped
public class UsuarioController {

    private UsuarioModel model = null;
    private Usuario usuario;
    private boolean estadoLogin;

    public UsuarioController() {
	model = new UsuarioModel(UsuarioModel.BANCODADOS);
	usuario = new Usuario();
	estadoLogin = true;
    }

    public void inserirAction() {
	model.inserir(usuario);
    }

    public void alterarAction() {
	model.alterar(usuario);
    }

    public void deletarAction() {
	model.deletar(usuario);
    }

    public Usuario buscarAction(int id) {
	return model.buscar(id);
    }

    public List<Usuario> buscarTodosAction() {
	return model.buscarTodos();
    }

    public void loginAction() {
	model.login(usuario.getUsername(), usuario.getPassword());
    }

    public void logoutAction() {
	model.logout();
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public boolean isEstadoLogin() {
	return estadoLogin;
    }

    public void setEstadoLogin(boolean estadoLogin) {
	this.estadoLogin = estadoLogin;
    }

    public void limpar() {
	usuario = new Usuario();
    }

    public void preparaCadastrar() {
	limpar();
	estadoLogin = false;
    }

    public void preparaLogin() {
	limpar();
	estadoLogin = true;
    }
}
