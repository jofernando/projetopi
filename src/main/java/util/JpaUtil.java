/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Fernando
 */
public class JpaUtil {

    private static EntityManagerFactory factory = null;

    private JpaUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory(String pu) {
	if (factory == null) {
	    factory = Persistence.createEntityManagerFactory(pu);
	}
	return factory;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
	if (factory == null) {
	    factory = Persistence.createEntityManagerFactory("PersistenceUnited");
	}
	return factory;
    }

    public static void close() {
	factory.close();
	factory = null;
    }
}
