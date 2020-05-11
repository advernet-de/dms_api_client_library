/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

/**
 * Fehlerantwort
 * 
 * @author Advernet.de GmbH
 */

public class ErrorResponse {

	private String error;
	private int status;
	private String errorDescription;

	/**
	 * Erstellt neue Fehlerantwort mit dem angegebene Statuscode
	 * 
	 * @param status HTTP-Status
	 */
	public ErrorResponse(int status) {
		this.status = status;
	}

	/**
	 * Gibt den HTTP-Status zurück
	 * 
	 * @return status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Setzt den HTTP-Status
	 * 
	 * @param status HTTP-Status 
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Gibt den Fehlertext zurück
	 * 
	 * @return Fehlertext
	 */
	public String getError() {
		return error;
	}

	/**
	 * Setzt den Fehlertext
	 * 
	 * @param error Fehlertext
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Gibt Fehlerdetails zurück
	 * 
	 * @return Fehlerdetails
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * Setzt die Fehlerdetails
	 * 
	 * @param errorDescription Fehlerdetails
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}


}
