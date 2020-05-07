/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client.examples;

import java.io.IOException;
import java.util.Scanner;

import de.advernet.api.client.*;

public class AccessTokenRequestExample {

	public static void main(String[] args) throws IOException {

		AccessTokenRequester requester = new AccessTokenRequester();

		Scanner scanner = new Scanner(System.in);

		System.out.print("url_string:    ");
		requester.setUrlString(scanner.nextLine());

		System.out.print("client_id:     ");
		requester.setClientId(scanner.nextLine());

		System.out.print("client_secret: ");
		requester.setClientSecret(scanner.nextLine());

		scanner.close();

		AccessTokenResponse token = requester.requestToken();
		System.out.println("statuscode:    " + token.getRequestStatus());

		if (token.getRequestStatus()==200) {
			System.out.println("access_token:  " + token.getAccessToken());
			System.out.println("token_type:    " + token.getTokenType());
			System.out.println("expires_in:    " + token.getExpiresIn());
		}

	}

}
