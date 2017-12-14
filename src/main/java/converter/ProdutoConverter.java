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
import model.dao.impl.BancoDeDadosProdutoDAO;
import model.entidades.Produto;

/**
 *
 * @author Fernando
 */
@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                BancoDeDadosProdutoDAO pc = new BancoDeDadosProdutoDAO();
                return pc.buscar(Integer.valueOf(value));
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
                return String.valueOf(((Produto) value).getId());
            } catch (Exception ex) {
                System.err.println("Falha ao fazer conversão. Erro: " + ex);
            }
        }
        return null;
    }

}
