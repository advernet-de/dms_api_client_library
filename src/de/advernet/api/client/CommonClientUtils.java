/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.io.PrintWriter;
import java.util.Random;

/**
 * Utilities
 * 
 * @author Advernet.de GmbH
 */

public class CommonClientUtils {

	private final static String API = "/api/v1";
	
	/**
	 * Form-Field hinzufügen
	 * 
	 * @param writer Zu Verwendender PrintWriter
	 * @param key Name des Feldes
	 * @param value Wert des Feldes
	 * @param boundary Trennzeichenkette
	 */
	public static void addFormField (PrintWriter writer, String key, String value, String boundary) {
		writer.append("--").append(boundary).append("\r\n");
		writer.append("Content-Disposition: form-data; name=\"").append(key).append("\"").append("\r\n");
		writer.append("\r\n");
		writer.append(value).append("\r\n");
		writer.flush();
	}

	/**
	 * Header für Datei-Upload hinzufügen
	 * 
	 * @param writer Zu Verwendender PrintWriter
	 * @param fieldname Name des Feldes
	 * @param filename Dateiname
	 * @param boundary Trennzeichenkette
	 */
	public static void addFilePartHeader (PrintWriter writer, String fieldname, String filename, String boundary) {
		writer.append("--").append(boundary).append("\r\n");
		writer.append("Content-Disposition: form-data; name=\"").append(fieldname).append("\"").append("; filename=\"").append(filename).append("\"").append("\r\n");
		writer.append("Content-Type: application/binary").append("\r\n");
		writer.append("Content-Transfer-Encoding: binary").append("\r\n");
		writer.append("\r\n");
		writer.flush();
	}

	/**
	 * Zufällige Trennzeichenkette erzeugen
	 * 
	 * @return Zufällige Trennzeichenkette
	 */
	public static String getBoundary() {
		String boundaryChars = "'()+,-./0123456789:=?ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";
		String boundary = "";
		Random random = new Random();
		for (int i=0;i<68;i++) {
			boundary=boundary+boundaryChars.charAt(random.nextInt(boundaryChars.length()));
		}
		return boundary;
	}

	/**
	 * Gibt den Pfad zur API zurück
	 * 
	 * @return Pfad zur API
	 */
	public static String getAPI() {
		return API;
	}

}
