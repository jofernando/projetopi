/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Fernando
 */
public class CriptografiaUtil {

    private static MessageDigest md = null;

    static {
	try {
	    md = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException e) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro! " + e, null));
	}
    }

    private static char[] hexCodes(byte[] text) {
	char[] hexOutput = new char[text.length * 2];
	String hexString;

	for (int i = 0; i < text.length; i++) {
	    hexString = "00" + Integer.toHexString(text[i]);
	    hexString.toUpperCase().getChars(hexString.length() - 2,
		    hexString.length(), hexOutput, i * 2);
	}
	return hexOutput;
    }

    public static String criptografar(String pwd) {
	if (md != null) {
	    return new String(hexCodes(md.digest(pwd.getBytes())));
	}
	return null;
    }
}
