package model.dao.impl;

import java.io.Serializable;

public class ConsultaSemResultadosException extends RuntimeException implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaSemResultadosException() {
	}

	public ConsultaSemResultadosException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConsultaSemResultadosException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConsultaSemResultadosException(String message) {
		super(message);
	}

	public ConsultaSemResultadosException(Throwable cause) {
		super(cause);
	}
	
}
