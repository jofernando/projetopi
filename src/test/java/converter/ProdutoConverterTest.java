/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import helper.DbUnitHelper;
import model.dao.impl.BancoDeDadosProdutoDAO;
import model.entidades.Produto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.JpaUtil;

/**
 *
 * @author fernando
 */
public class ProdutoConverterTest {

    private static DbUnitHelper dbUnitHelper;
    private static BancoDeDadosProdutoDAO bancoDeDadosProdutoDAO;

    public ProdutoConverterTest() {
    }

    @Test
    public void deveFazerConversaoStringParaObjeto() {
        assertEquals(produto1(), new ProdutoConverter().getAsObject(null, null, "1"));
    }

    @Test
    public void deveFazerConversaoObjetoParaString() {
        assertEquals("1", new ProdutoConverter().getAsString(null, null, produto1()));
    }

    @Test
    public void deveRetornarStringNula() {
        Assert.assertNull(new ProdutoConverter().getAsString(null, null, null));
    }

    @Test
    public void deveRetornarObjetoNulo() {
        Assert.assertNull(new ProdutoConverter().getAsObject(null, null, "0"));
    }

    public Produto produto1() {
        Produto produto = new Produto("1111-1", "primeiro", 11.1);
        produto.setId(1);
        return produto;
    }

    @BeforeClass
    public static void setUpClass() {
        bancoDeDadosProdutoDAO = new BancoDeDadosProdutoDAO("PersistenceUnitedTest");
        dbUnitHelper = new DbUnitHelper();
    }

    @AfterClass
    public static void tearDownClass() {
        JpaUtil.close();
    }

    @Before
    public void setUp() {
        dbUnitHelper.cleanInsert("/tabelas/Produto.xml");
    }

    @After
    public void tearDown() {
        dbUnitHelper.deleteAll("/tabelas/Produto.xml");
    }

}
