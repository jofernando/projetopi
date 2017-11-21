/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fernando
 */
public class SessionUtil {

    public static HttpSession getSession() {
	FacesContext ctx = FacesContext.getCurrentInstance();
	HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
	return sessao;
    }

    public static void setAttribute(String key, Object value) {
	getSession().setAttribute(key, value);
    }

    public static Object getAttribute(String key) {
	return getSession().getAttribute(key);
    }

    public static void removeAttribute(String key) {
	getSession().removeAttribute(key);
    }

    public static void invalidate() {
	getSession().invalidate();
    }
}
