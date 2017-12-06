/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author fernando
 */
public class FacesUtil {

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static void adicionarMensagemErro(String id, String sumary, String detail) {
        getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_ERROR, sumary, detail));
    }

    public static void adicionarMensagemAviso(String id, String sumary, String detail) {
        getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_INFO, sumary, detail));
    }

    public static void adicionarMessagemPerigo(String id, String sumary, String detail) {
        getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_WARN, sumary, detail));
    }

    public static void adicioanarMessagemFatal(String id, String sumary, String detail) {
        getFacesContext().addMessage(id, new FacesMessage(FacesMessage.SEVERITY_FATAL, sumary, detail));
    }
}
