/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

/**
 * Einzelergebnis aus Upload
 * 
 * @author Gabriel Aleanakian, Advernet.de GmbH
 */

public class MultiStatusElement {

	private int status;
	private String description;
	private String fileName;
	private String hash;
	private String href;

	/**
	 * Gibt den HTTP-Status zurück
	 * 
	 * @return HTTP-Status
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
	 * Gibt Details zum Upload zurück
	 * 
	 * @return Details
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setzt Details zum Upload
	 * 
	 * @param description Details zum Upload
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gibt den Dateinamen zurück
	 * 
	 * @return Dateiname
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setzt den Dateinamen
	 * 
	 * @param fileName Dateiname
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gibt die Prüfsumme der Datei zurück
	 * 
	 * @return Prüfsumme der Datei
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Setzt die Prüfsumme der Datei
	 * 
	 * @param hash Prüfsumme der Datei
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Gibt die Referenz zum Dokument im DMS zurück
	 * 
	 * @return Referenz zum Dokument im DMS
	 */
	public String getHref() {
		return href;
	}

	/**
	 * Setzt die Referenz zum Dokument im DMS
	 * 
	 * @param href Referenz zum Dokument im DMS
	 */
	public void setHref(String href) {
		this.href = href;
	}

}
