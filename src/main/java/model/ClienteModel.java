/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.impl.BancoDeDadosClienteDAO;
import model.dao.interfaces.ClienteDAO;
import model.dao.interfaces.DAO;
import model.entidades.Cliente;

/**
 *
 * @author Fernando
 */
public class ClienteModel {

    private DAO<Cliente> model = null;
    public static final int BANCODADOS = 1;

    public ClienteModel(int tipoArmazenamento) {
        if (tipoArmazenamento == BANCODADOS) {
            model = new BancoDeDadosClienteDAO();
        }
    }

    public ClienteModel(int tipoArmazenamento, String pu) {
        if (tipoArmazenamento == BANCODADOS) {
            model = new BancoDeDadosClienteDAO(pu);
        }
    }

    public boolean inserir(Cliente t) throws Exception {
        if (t == null) {
            throw new Exception("Cliente não pode ser nulo");
        } else if (t.getCpf().isEmpty()) {
            throw new Exception("CPF não pode ser nulo");
        } else if (((ClienteDAO) model).estaCadastrado(t)) {
            throw new Exception("Cliente com esse CPF já cadastrado");
        }
        return model.inserir(t);
    }

    public boolean alterar(Cliente t) throws Exception {
        if (t == null) {
            throw new Exception("Cliente não pode ser nulo");
        } else if (t.getCpf().isEmpty()) {
            throw new Exception("CPF não pode ser nulo");
        } else if (((ClienteDAO) model).estaCadastrado(t)) {
            throw new Exception("Cliente com esse CPF já cadastrado");
        }
        return model.alterar(t);
    }

    public boolean deletar(Cliente t) throws Exception {
        if (t == null) {
            throw new Exception("Selecione algum cliente para excluir");
        }
        return model.deletar(t);
    }

    public Cliente buscar(int id) {
        return model.buscar(id);
    }

    public List<Cliente> buscarTodos() {
        return model.buscarTodos();
    }

    public Cliente buscarPorCpf(String cpf) {
        return ((ClienteDAO) model).buscarPorCpf(cpf);
    }
}
