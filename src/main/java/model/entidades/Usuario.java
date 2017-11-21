/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Fernando
 */
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;

    public Usuario(String username, String password) {
	this.username = username;
	this.password = password;
    }

    public Usuario() {
    }

    public int getId() {
	return id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 89 * hash + this.id;
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Usuario other = (Usuario) obj;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Usuario{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
    }

}
