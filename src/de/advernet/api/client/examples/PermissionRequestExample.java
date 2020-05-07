/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client.examples;

import java.io.IOException;
import java.util.Scanner;

import de.advernet.api.client.*;

public class PermissionRequestExample {

	public static void main(String[] args) throws IOException {

		PermissionRequester requester = new PermissionRequester();

		Scanner scanner = new Scanner(System.in);

		System.out.print("url_string:    ");
		requester.setUrlString(scanner.nextLine());

		System.out.print("access_token:  ");
		requester.setToken(scanner.nextLine());

		scanner.close();

		requester.getPermissions();

		if (requester.getPermissionMap()!=null) {
			for (String key : requester.getPermissionMap().getPermissions().keySet()) {
				System.out.println(key + ": " + requester.getPermissionMap().getPermissions().get(key));
			}
		} else {
			if (requester.getErrorResponse()!=null) {
				System.out.println(requester.getErrorResponse().getStatus());
				System.out.println(requester.getErrorResponse().getError());
				System.out.println(requester.getErrorResponse().getErrorDescription());
			}
		}
	}

}
