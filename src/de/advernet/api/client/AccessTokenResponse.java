/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

/**
 * Response auf Access Token Request
 * 
 * @author Gabriel Aleanakian, Advernet.de GmbH
 */

public class AccessTokenResponse {

	private Integer requestStatus;
	private String accessToken;
	private String tokenType;
	private Integer expiresIn;

	/**
	 * Gibt den HTTP-Status zurück
	 * 
	 * @return HTTP-Status
	 */
	public Integer getRequestStatus() {
		return requestStatus;
	}

	/**
	 * Setzt den HTTP-Status
	 * 
	 * @param requestStatus HTTP-Status
	 */
	public void setRequestStatus(Integer requestStatus) {
		this.requestStatus = requestStatus;
	}

	/**
	 * Gibt das Access Token zurück
	 * 
	 * @return Access Token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Setzt das Access Token
	 * 
	 * @param accessToken Access Token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Gibt den Token Type zurück
	 * 
	 * @return Token Type
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * Setzt den Token Type
	 * 
	 * @param tokenType Token Type
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * Gibt die Dauer bis zu Ablauf der Token-Gültigkeit zurück
	 * 
	 * @return Token-Gültigkeit in Sekunden
	 */
	public Integer getExpiresIn() {
		return expiresIn;
	}

	/**
	 * Setzt die Token-Gültigkeit
	 * 
	 * @param expiresIn Token-Gültigkeit in Sekunden
	 */
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

}
