/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.dao.impl.BancoDeDadosClienteDAO;
import model.entidades.Cliente;

/**
 *
 * @author Fernando
 */
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                BancoDeDadosClienteDAO cc = new BancoDeDadosClienteDAO();
                return cc.buscar(Integer.valueOf(value));
            } catch (NumberFormatException ex) {
                System.err.println("Falha ao fazer conversão. Erro: " + ex);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            try {
                return String.valueOf(((Cliente) value).getId());
            } catch (Exception ex) {
                System.err.println("Falha ao fazer conversão. Erro:" + ex);
            }
        }
        return null;
    }
}
