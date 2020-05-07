/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Werkzeug zum Hochladen von Dokumenten
 * 
 * @author Gabriel Aleanakian, Advernet.de GmbH
 */

public class DocumentUploader {

	private final static String API = "/api/v1";

	private final static String UPLOADPATH = "/teams/<team>/boxes/Eingang/documents";

	private String urlString;
	private String team;
	private String token;

	private MultiStatus multiStatus;
	private ErrorResponse errorResponse;

	/**
	 * Dokumente hochladen
	 * 
	 * @param docs Liste mit Dokumenten
	 * @throws IOException
	 */
	public void uploadDocumentList(DocumentList docs) throws IOException {

		HttpURLConnection httpConnection = null;
		URL url = new URL(urlString + API + UPLOADPATH.replaceAll("<team>", team));
		OutputStream os = null;
		PrintWriter writer = null;

		boolean success = false;
		String boundary = null;

		while (!success) {

			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setInstanceFollowRedirects(false);
			httpConnection.setRequestMethod("POST");
			boundary = CommonClientUtils.getBoundary();
			httpConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);
			httpConnection.setRequestProperty("Authorization", "bearer " + token);
			httpConnection.setUseCaches(false);

			os = httpConnection.getOutputStream();
			writer = new PrintWriter(new OutputStreamWriter(os,"UTF-8"),true);

			boolean boundaryFound = false;

			for (DocumentListElement document : docs.getElements()) {
				byte[] data = document.getData();
				String dataAsString = new String(data,"UTF-8");
				if (!dataAsString.contains(boundary)) {
					String filename = document.getFilename();
					if (!filename.endsWith(".pdf")) filename = filename + ".pdf";
					CommonClientUtils.addFilePartHeader(writer,"file",filename,boundary);
					os.write(data);
					os.flush();
					writer.append("\r\n");
					writer.flush();
				} else {
					boundaryFound = true;
					break;
				}
			}

			if (boundaryFound) continue;

			success = true;

		}

		writer.append("--").append(boundary).append("--").append("\r\n");
		writer.flush();
		writer.close();

		os.close();

		int status = httpConnection.getResponseCode();
		InputStream stream;
		String info = null;
		try {
			stream = httpConnection.getInputStream();
		} catch (Exception e) {
			stream = httpConnection.getErrorStream();
		}

		if (stream!=null) {
			info = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
		}

		if (httpConnection!=null) httpConnection.disconnect();

		if (info!=null) {
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
			if (status==207) {
				setMultiStatus(gson.fromJson(info, MultiStatus.class));
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
	 * @return Team in das Dokumente hochgeladen werden sollen
	 */
	public String getTeam() {
		return team;
	}

	/**
	 * Setzt das Team im DMS
	 * 
	 * @param team Team in das Dokumente hochgeladen werden sollen
	 */
	public void setTeam(String team) {
		this.team = team;
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
	 * Gibt Liste mit Ergebnissen des Uploads zurück
	 * 
	 * @return Liste mit Ergebnissen des Uploads
	 */
	public MultiStatus getMultiStatus() {
		return multiStatus;
	}

	/**
	 * Setzt Liste mit Ergebnissen des Uploads
	 * 
	 * @param multiStatus Liste mit Ergebnissen des Uploads
	 */
	public void setMultiStatus(MultiStatus multiStatus) {
		this.multiStatus = multiStatus;
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

}
