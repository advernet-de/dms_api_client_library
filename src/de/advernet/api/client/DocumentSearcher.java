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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Werkzeug zum Suchen von Dokumenten
 * 
 * @author Advernet.de GmbH
 */

public class DocumentSearcher {

	private final static String PATH_PATTERN = "/teams/<team>/boxes/<box>/documents";

	private String urlString;
	private String team;
	private String box;
	private String token;

	private String date;
	private String dateFrom;
	private String dateTo;
	private String documentType;
	private List<String> tags = new ArrayList<String>();

	private DocumentInfoList documentInfoList;
	private ErrorResponse errorResponse;

	/**
	 * Suchabfrage
	 * 
	 * @throws IOException
	 */
	public void searchDocuments() throws IOException {
		
		if (team==null || box==null) {
			throw new IllegalArgumentException();
		}

		HttpURLConnection httpConnection = null;
		
		String delimiter = "?";
		String base = urlString + CommonClientUtils.getAPI() + PATH_PATTERN
					.replaceAll("<team>", URLEncoder.encode(team,"UTF-8").replaceAll("\\+", "%20"))
					.replaceAll("<box>", URLEncoder.encode(box,"UTF-8").replaceAll("\\+", "%20"));
		
		String query = "";
		
		if (date!=null && !date.isEmpty()) {
			query = query + delimiter + "date=" + URLEncoder.encode(date, "UTF-8");
			delimiter = "&";
		}
		if (dateFrom!=null && !dateFrom.isEmpty()) {
			query = query + delimiter + "date_from=" + URLEncoder.encode(dateFrom, "UTF-8");
			delimiter = "&";
		}
		if (dateTo!=null && !dateTo.isEmpty()) {
			query = query + delimiter + "date_to=" + URLEncoder.encode(dateTo, "UTF-8");
			delimiter = "&";
		}
		if (documentType!=null && !documentType.isEmpty()) {
			query = query + delimiter + "document_type=" + URLEncoder.encode(documentType, "UTF-8");
			delimiter = "&";
		}
		for (String tag : tags) {
			query = query + delimiter + "tag=" + URLEncoder.encode(tag, "UTF-8");
			delimiter = "&";
		}
		
		URL url = new URL(base + query);
		httpConnection = (HttpURLConnection) url.openConnection();

		httpConnection.setDoOutput(true);
		httpConnection.setInstanceFollowRedirects(false);
		httpConnection.setRequestMethod("GET");

		httpConnection.setRequestProperty("Authorization", "bearer " + token);
		httpConnection.setUseCaches(false);

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
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
			if (status==200) {
				setDocumentInfoList(gson.fromJson(info, DocumentInfoList.class));
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
	 * Gibt das Datum zurück
	 * 
	 * @return Datum
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Setzt das Datum
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gibt das Anfangsdatum für die Suche zurück
	 *  
	 * @return Anfangsdatum für die Suche
	 */
	public String getDateFrom() {
		return dateFrom;
	}
	
	/**
	 * Setzt Anfangsdatum für die Suche
	 * 
	 * @param dateFrom Anfangsdatum für die Suche
	 */
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	/**
	 * Gibt das Endedatum für die Suche zurück
	 * 
	 * @return Endedatum für die Suche
	 */
	public String getDateTo() {
		return dateTo;
	}
	
	/**
	 * Setzt das Endedatum für die Suche
	 * 
	 * @param dateTo Endedatum für die Suche
	 */
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
	/**
	 * Gibt den Dokumententyp aus dem Suchfilter zurück
	 * 
	 * @return Dokumententyp aus dem Suchfilter
	 */
	public String getDocumentType() {
		return documentType;
	}
	
	/**
	 * Gibt den Dokumententyp aus dem Suchfilter zurück
	 * 
	 * @param documentType Dokumententyp aus dem Suchfilter
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	/**
	 * Gibt die Schlagworte aus dem Suchfilter zurück
	 * 
	 * @return Schlagworte aus dem Suchfilter
	 */
	public List<String> getTags() {
		return tags;
	}
	
	/**
	 * Setzt die Schlagworte aus dem Suchfilter
	 * 
	 * @param tags Schlagworte aus dem Suchfilter
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	/**
	 * Fügt ein Schlagwort zum Suchfilter hinzu
	 * 
	 * @param tag Schlagwort
	 */
	public void addTag(String tag) {
		this.tags.add(tag);
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

}
