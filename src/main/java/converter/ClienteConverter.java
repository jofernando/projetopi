/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import controller.ClienteController;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
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
		ClienteController cc = new ClienteController();
		return cc.buscarAction(Integer.valueOf(value));
	    } catch (NumberFormatException ex) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", ex.getMessage()));
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
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", ex.getMessage()));
	    }
	}
	return null;
    }
}
