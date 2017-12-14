/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.impl;

import helper.DbUnitHelper;
import model.entidades.Usuario;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import util.CriptografiaUtil;
import util.JpaUtil;

/**
 *
 * @author fernando
 */
public class BancoDeDadosUsuarioDAOTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static BancoDeDadosUsuarioDAO bancoDeDadosUsuarioDAO;
    private static DbUnitHelper dbUnitHelper;

    public BancoDeDadosUsuarioDAOTest() {
    }

    public Usuario usuario1() {
        Usuario usuario = new Usuario("primeiro", CriptografiaUtil.criptografar("senha"));
        usuario.setId(1);
        return usuario;
    }

    public Usuario usuario2() {
        Usuario usuario = new Usuario("segunda", CriptografiaUtil.criptografar("password"));
        usuario.setId(2);
        return usuario;
    }

    public Usuario novoUsuario() {
        return new Usuario("terceiro", CriptografiaUtil.criptografar("senhaaas"));
    }

    @Test
    public void deveLogar() {
        assertEquals(usuario1(), bancoDeDadosUsuarioDAO.login("primeiro", CriptografiaUtil.criptografar("senha")));
    }

    @Test
    public void deveEstarCadastrado() {
        assertTrue(bancoDeDadosUsuarioDAO.estaCadastrado(usuario1()));
    }

    @Test
    public void deveNaoEstarCadastrado() {
        assertFalse(bancoDeDadosUsuarioDAO.estaCadastrado(novoUsuario()));
    }

    @BeforeClass
    public static void setUpClass() {
        bancoDeDadosUsuarioDAO = new BancoDeDadosUsuarioDAO("PersistenceUnitedTest");
        dbUnitHelper = new DbUnitHelper();
    }

    @AfterClass
    public static void tearDownClass() {
        JpaUtil.close();
    }

    @Before
    public void setUp() {
        dbUnitHelper.cleanInsert("/tabelas/Usuario.xml");
    }

    @After
    public void tearDown() {
//        dbUnitHelper.deleteAll("/tabelas/Usuario.xml");
    }

}
