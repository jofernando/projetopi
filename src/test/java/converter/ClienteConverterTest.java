/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import helper.DbUnitHelper;
import javax.faces.context.FacesContext;
import model.dao.impl.BancoDeDadosClienteDAO;
import model.entidades.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import util.JpaUtil;

/**
 *
 * @author fernando
 */
public class ClienteConverterTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private static BancoDeDadosClienteDAO bancoDeDadosClienteDAO;
    private static DbUnitHelper dbUnitHelper;

    public ClienteConverterTest() {
    }

    public Cliente cliente1() {
        Cliente cliente
                = new Cliente("José", "jose@gmail.com", "(11)11111-1111", "Rua primeira", "111.111.111-11");
        cliente.setId(1);
        return cliente;
    }

    @Test
    public void deveFazerConversaoParaObjeto() {
        assertEquals(cliente1(), (Cliente) new ClienteConverter().getAsObject(FacesContext.getCurrentInstance(), null, "1"));
    }

    @Test
    public void deveFazerConversaoParaString() {
        assertEquals("1", new ClienteConverter().getAsString(null, null, cliente1()));
    }

    @Test
    public void deveFazerConversaoParaStringNula() {
        assertNull(new ClienteConverter().getAsString(null, null, null));
    }

    @Test
    public void deveFazerConversaoParaObjetoNulo() {
        assertNull(new ClienteConverter().getAsObject(null, null, "0"));
    }

    @BeforeClass
    public static void setUpClass() {
        bancoDeDadosClienteDAO = new BancoDeDadosClienteDAO("PersistenceUnitedTest");
        dbUnitHelper = new DbUnitHelper();
    }

    @AfterClass
    public static void tearDownClass() {
        JpaUtil.close();
    }

    @Before
    public void setUp() {
        dbUnitHelper.cleanInsert("/tabelas/Cliente.xml");
    }

    @After
    public void tearDown() {
        dbUnitHelper.deleteAll("/tabelas/Cliente.xml");
    }

}
