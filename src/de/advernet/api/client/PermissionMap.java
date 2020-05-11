/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.util.List;
import java.util.Map;

/**
 * Datenstruktur mit Berechtigungen
 * 
 * @author Advernet.de GmbH
 */

public class PermissionMap {

	private Map<String, List<String>> permissions;

	/**
	 * Gibt Datenstruktur mit Berechtigungen zurück
	 * 
	 * @return Berechtigungen
	 */
	public Map<String, List<String>> getPermissions() {
		return permissions;
	}

	/**
	 * Setzt die Datenstruktur mit Berechtigungen
	 * 
	 * @param permissions Berechtigungen
	 */
	public void setPermissions(Map<String, List<String>> permissions) {
		this.permissions = permissions;
	}

}
