/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.util.List;

/**
 * Metadaten zu einem Dokument
 * 
 * @author Advernet.de GmbH
 */

public class DocumentInfo {

	private int documentNo;
	private String documentType;
	private String documentDate;
	private List<String> documentTags;
	private String documentStampText;
	private String documentStampColor;
	private boolean documentIsLocked;
	private String documentNote;
	private String fileName;
	private int fileSize;
	private String fileHash;
	private String metaHash;

	/**
	 * Gibt die Dokumentennummer zurück 
	 * 
	 * @return Dokumentennummer
	 */
	public int getDocumentNo() {
		return documentNo;
	}
	
	/**
	 * Setzt die Dokumentennummer
	 * 
	 * @param documentNo Dokumentennummer
	 */
	public void setDocumentNo(int documentNo) {
		this.documentNo = documentNo;
	}
	
	/**
	 * Gibt den Dokumententyp zurück
	 * 
	 * @return Dokumententyp
	 */
	public String getDocumentType() {
		return documentType;
	}
	
	/**
	 * Setzt den Dokumententyp
	 * 
	 * @param documentType Dokumententyp
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	/**
	 * Gibt das Dokumentendatum zurück
	 * 
	 * @return Dokumentendatum
	 */
	public String getDocumentDate() {
		return documentDate;
	}
	
	/**
	 * Setzt das Dokumentendatum
	 * 
	 * @param documentDate Dokumentendatum
	 */
	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}
	
	/**
	 * Gibt die zugeordneten Schlagworte zurück 
	 * 
	 * @return Liste mit Schlagworten
	 */
	public List<String> getDocumentTags() {
		return documentTags;
	}
	
	/**
	 * Setzt die Liste der Schlagworte
	 * 
	 * @param documentTags Liste mit Schlagworten
	 */
	public void setDocumentTags(List<String> documentTags) {
		this.documentTags = documentTags;
	}
	
	/**
	 * Gibt den Stempeltext zurück
	 * 
	 * @return Stempeltext
	 */
	public String getDocumentStampText() {
		return documentStampText;
	}
	
	/**
	 * Setzt den Stempeltext
	 * 
	 * @param documentStampText Stempeltext
	 */
	public void setDocumentStampText(String documentStampText) {
		this.documentStampText = documentStampText;
	}
	
	/**
	 * Gibt die Stempelfarbe zurück
	 * 
	 * @return Stempelfarbe
	 */
	public String getDocumentStampColor() {
		return documentStampColor;
	}
	
	/**
	 * Setzt die Stempelfarbe
	 * 
	 * @param documentStampColor Stempelfarbe
	 */
	public void setDocumentStampColor(String documentStampColor) {
		this.documentStampColor = documentStampColor;
	}
	
	/**
	 * Gibt die Information zurück, ob das Dokument gesperrt ist 
	 * 
	 * @return Information, ob das Dokument gesperrt ist
	 */
	public boolean getDocumentIsLocked() {
		return documentIsLocked;
	}
	
	/**
	 * Setzt die Information, ob das Dokument gesperrt ist
	 * 
	 * @param documentIsLocked Information, ob das Dokument gesperrt ist
	 */
	public void setDocumentIsLocked(boolean documentIsLocked) {
		this.documentIsLocked = documentIsLocked;
	}
	
	/**
	 * Gibt die Notiz zum Dokument zurück
	 * 
	 * @return Notiz zum Dokument
	 */
	public String getDocumentNote() {
		return documentNote;
	}

	/**
	 * Setzt die Notiz zum Dokument
	 * 
	 * @param documentNote Notiz zum Dokument
	 */
	public void setDocumentNote(String documentNote) {
		this.documentNote = documentNote;
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
	 * Setzte den Dateinamen
	 * 
	 * @param fileName Dateiname
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Gibt die Dateigröße zurück
	 * 
	 * @return Dateigröße
	 */
	public int getFileSize() {
		return fileSize;
	}
	
	/**
	 * Setzt die Dateigröße
	 * 
	 * @param fileSize Dateigröße
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	/**
	 * Gibt die Prüfsumme der Datei zurück
	 * 
	 * @return Prüfsumme der Datei
	 */
	public String getFileHash() {
		return fileHash;
	}
	
	/**
	 * Setzt die Prüfsumme der Datei
	 * 
	 * @param fileHash Prüfsumme der Datei
	 */
	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	
	/**
	 * Gibt die Prüfsumme der Metadaten zurück
	 * 
	 * @return Prüfsumme der Metadaten
	 */
	public String getMetaHash() {
		return metaHash;
	}
	
	/**
	 * Setzt die Prüfsumme der Metadaten
	 * 
	 * @param metaHash Prüfsumme der Metadaten
	 */
	public void setMetaHash(String metaHash) {
		this.metaHash = metaHash;
	}

}
