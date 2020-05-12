/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.util.List;

/**
 * Liste mit Metadaten von Dokumenten
 * 
 * @author Advernet.de GmbH
 */

public class DocumentInfoList {

	private List<DocumentInfo> documents;

	/**
	 * Gibt die Liste mit Metadaten zurück
	 * 
	 * @return Liste mit Metadaten
	 */
	public List<DocumentInfo> getDocuments() {
		return documents;
	}

	/**
	 * Setzt die Liste mit Metadaten
	 * 
	 * @param documents Liste mit Metadaten
	 */
	public void setDocuments(List<DocumentInfo> documents) {
		this.documents = documents;
	}
	
	/**
	 * Fügt Metadaten zu einem Dokument hinzu
	 * 
	 * @param documentInfo Metadaten zu einem Dokument
	 */
	public void addDocumentInfo(DocumentInfo documentInfo) {
		getDocuments().add(documentInfo);
	}

}
