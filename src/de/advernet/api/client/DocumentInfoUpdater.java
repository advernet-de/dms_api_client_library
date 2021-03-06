/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Werkzeug zum Ändern von Metainformationen
 * 
 * @author Advernet.de GmbH
 */

public class DocumentInfoUpdater {

	private final static String PATH_PATTERN = "/teams/<team>/boxes/<box>/documents/<document_no>/info";

	private String urlString;
	private String team;
	private String box;
	private String documentNo;
	private String token;

	private DocumentInfoList documentInfoList;
	private ErrorResponse errorResponse;
	
	private String success;

	/**
	 * Metainformationen ändern
	 * 
	 * @throws IOException
	 */
	public void updateDocumentInfo() throws IOException {

		if (team==null || box==null || documentNo==null) {
			throw new IllegalArgumentException();
		}
		
		HttpURLConnection httpConnection = null;
		URL url = new URL(urlString + CommonClientUtils.getAPI() + PATH_PATTERN
				.replaceAll("<team>", URLEncoder.encode(team,"UTF-8").replaceAll("\\+", "%20"))
				.replaceAll("<box>", URLEncoder.encode(box,"UTF-8").replaceAll("\\+", "%20"))
				.replaceAll("<document_no>", URLEncoder.encode(documentNo,"UTF-8").replaceAll("\\+", "%20"))
				);

		httpConnection = (HttpURLConnection) url.openConnection();

		httpConnection.setDoOutput(true);
		httpConnection.setInstanceFollowRedirects(false);
		httpConnection.setRequestMethod("PUT");

		httpConnection.setRequestProperty("Authorization", "bearer " + token);
		httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

		httpConnection.setUseCaches(false);

		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

		OutputStream os = httpConnection.getOutputStream();
		byte[] json = gson.toJson(documentInfoList, DocumentInfoList.class).getBytes("UTF-8");
		os.write(json);
		
		int status = httpConnection.getResponseCode();

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
			gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
			if (status==200) {
				setSuccess("true");
			} else {
				setErrorResponse(gson.fromJson(info, ErrorResponse.class));
			}
		} else {
			setErrorResponse(new ErrorResponse(status));
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
	 * Gibt das Team des DMS zurück
	 * 
	 * @return Team des DMS
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * Setzt das Team im DMS
	 * 
	 * @param team Team im DMS
	 */
	public void setTeam(String team) {
		this.team = team;
	}
	
	/**
	 * Gibt das Ablagefach zurück
	 * 
	 * @return Ablagefach
	 */
	public String getBox() {
		return box;
	}

	/**
	 * Setzt das Ablagefach
	 * 
	 * @param box Ablagefach
	 */
	public void setBox(String box) {
		this.box = box;
	}

	/**
	 * Gibt die Dokumentennummer zurück
	 * 
	 * @return Dokumentennummer
	 */
	public String getDocumentNo() {
		return documentNo;
	}

	/**
	 * Setzt die Dokumentennummer
	 * 
	 * @param documentNo Dokumentennummer
	 */
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
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
	 * Gibt die Liste mit Metainformationen zurück
	 * 
	 * @return Liste mit Metainformationen
	 */
	public DocumentInfoList getDocumentInfoList() {
		return documentInfoList;
	}

	/**
	 * Setzt die Liste mit Metainformationen
	 * 
	 * @param documentInfoList Liste mit Metainformationen
	 */
	public void setDocumentInfoList(DocumentInfoList documentInfoList) {
		this.documentInfoList = documentInfoList;
	}

	/**
	 * Gibt Fehlerantwort zurück
	 * 
	 * @return Fehlerantwort
	 */
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	/**
	 * Setzt Fehlerantwort
	 * 
	 * @param errorResponse Fehlerantwort
	 */
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	/**
	 * Initialisiert die Metadaten-Liste mit dem Hash der aktuellen Metadaten 
	 * 
	 * @param metaHash Hash der aktuellen Metadaten des Dokumentes
	 */
	public void initDocumentInfoList(String metaHash) {
		this.documentInfoList = new DocumentInfoList();
		DocumentInfo current = new DocumentInfo();
		current.setMetaHash(metaHash);
		documentInfoList.setDocuments(new ArrayList<DocumentInfo>());
		documentInfoList.getDocuments().add(current);
	}

	/**
	 * Gibt Wert des Strings mit Bestätigung bei erfolgreichem Zugriff zurück
	 * 
	 * @return String mit Bestätigung bei erfolgreichem Zugriff
	 */
	public String getSuccess() {
		return success;
	}

	/**
	 * Setzt den String mit Bestätigung bei erfolgreichem Zugriff
	 * 
	 * @param success String mit Bestätigung bei erfolgreichem Zugriff
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	
}
