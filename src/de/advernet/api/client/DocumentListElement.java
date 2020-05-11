/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

/**
 * Hochzuladendes Dokument
 * 
 * @author Advernet.de GmbH
 */

public class DocumentListElement {

	private String filename = null;
	private byte[] data = null;

	/**
	 * Neues Element erstellen
	 * 
	 * @param filename Dateiname
	 * @param data Dateiinhalt als Byte-Array
	 */
	public DocumentListElement(String filename, byte[] data) {
		setFilename(filename);
		setData(data);
	}

	/**
	 * Gibt den Dateinamen zurück
	 * 
	 * @return Dateiname
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Setzt den Dateinamen
	 * 
	 * @param filename Dateiname
	 */
  	public void setFilename(String filename) {
		this.filename = filename;
	}

  	/**
  	 * Gibt den Dateiinhalt zurück
  	 * 
  	 * @return Inhalt des Dokumentes
  	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Setzt den Dateiinhalt
	 * 
	 * @param data Inhalt des Dokumentes als Byte-Array
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

}
