/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Werkzeug zum Abfragen von Berechtigungen
 * 
 * @author Advernet.de GmbH
 */

public class PermissionRequester {

	private final static String PATH = "/permissions";

	private String urlString;
	private String token;

	private PermissionMap permissionMap;
	private ErrorResponse errorResponse;
	private Integer status;

	/**
	 * Berechtigungen abfragen
	 * 
	 * @throws IOException
	 */
	public void loadPermissions() throws IOException {

		HttpURLConnection httpConnection;
		URL url = new URL(urlString + CommonClientUtils.getAPI() + PATH);

		httpConnection = (HttpURLConnection) url.openConnection();

		httpConnection.setDoOutput(true);
		httpConnection.setInstanceFollowRedirects(false);
		httpConnection.setRequestMethod("GET");

		httpConnection.setRequestProperty("Authorization", "bearer " + token);
		httpConnection.setUseCaches(false);

		setStatus(httpConnection.getResponseCode());

		InputStream stream;
		String info = null;
		try {
			stream = httpConnection.getInputStream();
		} catch (IOException e) {
			stream = httpConnection.getErrorStream();
		}

		if (stream!=null) {
			info = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
		}

		if (httpConnection!=null) httpConnection.disconnect();

		if (info!=null) {
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
			if (getStatus()==200) {
				setPermissionMap(gson.fromJson(info, PermissionMap.class));
			} else {
				setErrorResponse(gson.fromJson(info, ErrorResponse.class));
			}
		} else {
			setErrorResponse(new ErrorResponse(getStatus()));
		}

	}

	/**
	 * Gibt den URL-String zurück
	 * 
	 * @return URL-String der anzusprechenden DMS-Instanz
	 */
	public String getUrlString() {
		return urlString;
	}

	/**
	 * Setzt den URL-String
	 * 
	 * @param urlString URL-String
	 */
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	/**
	 * Gibt das Access Token zurück
	 * 
	 * @return Access Token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Setzt das Access Token
	 * 
	 * @param token Access Token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gibt Datenstruktur mit Berechtigungen zurück
	 * 
	 * @return Berechtigungen
	 */
	public PermissionMap getPermissionMap() {
		return permissionMap;
	}

	/**
	 * Setzt die Datenstruktur mit Berechtigungen
	 * 
	 * @param permissionsMap Berechtigungen
	 */
	public void setPermissionMap(PermissionMap permissionMap) {
		this.permissionMap = permissionMap;
	}

	/**
	 * Gibt eine Fehlerantwort zurück
	 * 
	 * @return Fehlerantwort
	 */
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	/**
	 * Setzt eine Fehlerantwort
	 * 
	 * @param errorResopnse Fehlerantwort
	 */
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	/**
	 * Gibt den HTTP-Status zurück
	 * 
	 * @return HTTP-Status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Setzt den HTTP-Status
	 * 
	 * @param status HTTP-Status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
