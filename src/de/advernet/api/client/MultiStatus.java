/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.util.List;

/**
 * Ergebisliste aus Upload
 * 
 * @author Gabriel Aleanakian, Advernet.de GmbH
 */

public class MultiStatus {

	private List<MultiStatusElement> results;

	/**
	 * Gibt die Ergebnisliste zurück
	 * 
	 * @return Liste
	 */
	public List<MultiStatusElement> getResults() {
		return results;
	}

	/**
	 * Setzt die Ergebnisliste
	 * 
	 * @param results Liste
	 */
	public void setResults(List<MultiStatusElement> results) {
		this.results = results;
	}

}
