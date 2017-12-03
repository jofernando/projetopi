/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.dao.impl.BancoDeDadosUsuarioDAO;
import model.dao.interfaces.DAO;
import model.dao.interfaces.UsuarioDAO;
import model.entidades.Usuario;
import util.CriptografiaUtil;
import util.SessionUtil;

/**
 *
 * @author Fernando
 */
public class UsuarioModel {

	private DAO<Usuario> model = null;
	public static final int BANCODADOS = 1;

	public UsuarioModel(int tipoArmazenamento) {
		if (tipoArmazenamento == BANCODADOS) {
			model = new BancoDeDadosUsuarioDAO();
		}
	}

	public UsuarioModel(int tipoArmazenamento, String pu) {
		if (tipoArmazenamento == BANCODADOS) {
			model = new BancoDeDadosUsuarioDAO(pu);
		}
	}

	public boolean inserir(Usuario t) {
		if (t == null) {
			throw new IllegalArgumentException("Usuário não pode ser nulo");
		} else if (t.getPassword().isEmpty()) {
			throw new IllegalArgumentException("Senha não pode ser nulo");
		} else if (((UsuarioDAO) model).estaCadastrado(t)) {
			throw new IllegalArgumentException("Usuário com esse nome já cadastrado");
		} else if (t.getUsername().isEmpty()) {
			throw new IllegalArgumentException("Nome do usuário não pode ser nulo");
		}
		t.setPassword(CriptografiaUtil.criptografar(t.getPassword()));
		return model.inserir(t);

	}

	public boolean alterar(Usuario t) {
		if (t == null) {
			throw new IllegalArgumentException("Usuário não pode ser nulo");
		} else if (t.getUsername().isEmpty()) {
			throw new IllegalArgumentException("Nome do usuário não pode ser nulo");
		} else if (t.getPassword().isEmpty()) {
			throw new IllegalArgumentException("Senha não pode ser nula");
		} else if (((UsuarioDAO) model).estaCadastrado(t)) {
			throw new IllegalArgumentException("Usuário com esse nome já cadastrado");
		}
		t.setPassword(CriptografiaUtil.criptografar(t.getPassword()));
		return model.alterar(t);
	}

	public boolean deletar(Usuario t) {
		if (t == null) {
			throw new IllegalArgumentException("Selecione algum usuário para excluir");
		}
		return model.deletar(t);
	}

	public Usuario buscar(int id) {
		return model.buscar(id);
	}

	public List<Usuario> buscarTodos() {
		return model.buscarTodos();
	}

	public boolean login(String username, String password) {
		password = CriptografiaUtil.criptografar(password);
		Usuario usuario = ((UsuarioDAO) model).login(username, password);
		if (usuario != null) {
			SessionUtil.setAttribute("UsuarioLogado", usuario);
			return true;
		} else {
			throw new IllegalArgumentException("Usuario e/ou senha inválidos");
		}
	}

	public void logout() {
		SessionUtil.invalidate();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário deslogado", null));
	}
}
