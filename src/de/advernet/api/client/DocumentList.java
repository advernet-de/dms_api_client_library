/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Liste von hochzuladenden Dokumenten
 * 
 * @author Gabriel Aleanakian, Advernet.de GmbH
 */

public class DocumentList {

	private List<DocumentListElement> elements = new ArrayList<DocumentListElement>();

	/**
	 * Gibt die Liste der hochzuladenden Dokumente
	 * 
	 * @return Liste der Dokumente
	 */
	public List<DocumentListElement> getElements() {
		return elements;
	}

	/**
	 * Dokument zur Liste hinzufügen
	 * 
	 * @param filename Dateiname
	 * @param data Dateiinhalt als Byte-Array
	 */
	public void add(String filename, byte[] data) {
		elements.add(new DocumentListElement(filename, data));
	}

	/**
	 * Liste der hochzuladenden Dokumente löschen
	 */
	public void clear() {
		elements.clear();
	}

}
