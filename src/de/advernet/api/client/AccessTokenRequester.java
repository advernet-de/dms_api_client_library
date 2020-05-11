/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Werkzeug zum Abfragen von Access Tokens
 * 
 * @author Advernet.de GmbH
 */
public class AccessTokenRequester {

	private final static String PATH = "/oauth2/token";
	private final static String GRANT_TYPE = "client_credentials";

	private String urlString;
	private String clientId;
	private String clientSecret;

	/**
	 * Access Token Request
	 * 
	 * @return {@link AccessTokenResponse}
	 * @throws IOException
	 */
	public AccessTokenResponse requestToken() throws IOException {

		AccessTokenResponse token = new AccessTokenResponse();
		String boundary = CommonClientUtils.getBoundary();
		URL url = new URL(getUrlString() + CommonClientUtils.getAPI() + PATH);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);
		conn.setUseCaches(false);
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()));
		CommonClientUtils.addFormField(writer,"grant_type",GRANT_TYPE,boundary);
		CommonClientUtils.addFormField(writer,"client_id",getClientId(),boundary);
		CommonClientUtils.addFormField(writer,"client_secret",getClientSecret(),boundary);
		writer.append("--").append(boundary).append("--").append("\r\n");
		writer.close();

		int status = conn.getResponseCode();
		token.setRequestStatus(status);
		if (status==200) {
			InputStream stream = conn.getInputStream();
			String jsonResponse = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
			if (conn!=null) conn.disconnect();
			Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
			Type type = new TypeToken<Map<String, String>>(){}.getType();
			Map<String, String> map = gson.fromJson(jsonResponse, type);
			token.setAccessToken(map.get("access_token"));
			token.setTokenType(map.get("token_type"));
			token.setExpiresIn(new Integer(map.get("expires_in")));
		} else {
			if (conn!=null) conn.disconnect();
		}
		return token;
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
	 * @param urlString URL-String der anzusprechenden DMS-Instanz
	 */
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	/**
	 * Gibt die Client ID zurück
	 * 
	 * @return Client ID
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Setzt die Client ID
	 * 
	 * @param clientId Client ID
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * Gibt das Client Secret zurück
	 * 
	 * @return Client Secret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * Setzt das Client Secret
	 * 
	 * @param clientSecret Client Secret
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

}
