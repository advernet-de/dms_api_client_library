/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

import de.advernet.api.client.*;

public class DocumentUploadExample {

	public static void main(String[] args) throws IOException {

		DocumentUploader uploader = new DocumentUploader();

		Scanner scanner = new Scanner(System.in);

		System.out.print("url_string:   ");
		uploader.setUrlString(scanner.nextLine());

		System.out.print("access_token: ");
		uploader.setToken(scanner.nextLine());

		System.out.print("Team:         ");
		uploader.setTeam(scanner.nextLine());

		DocumentList documentList = new DocumentList();

		System.out.println("Bitte Dateinamen aus dem aktuellen Verzeichnis angeben.");
		System.out.println("EOF-Markierung beendet die Eingabe.");

		System.out.print("Datei:        ");
		while (scanner.hasNextLine()) {
			File file = new File(scanner.nextLine());
			if (file.exists() && file.canRead() && file.isFile()) {
				documentList.add(file.getName(), Files.readAllBytes(file.toPath()));
			} else {
				System.out.println("Datei nicht gefunden oder nicht lesbar.");
			}
			System.out.print("Datei:        ");
		}
		scanner.close();

		if (documentList.getElements().size()>0) {
			uploader.uploadDocumentList(documentList);
		}

		System.out.println();
		if (uploader.getMultiStatus()!=null) {
			for (MultiStatusElement element : uploader.getMultiStatus().getResults()) {
				System.out.println(element.getStatus());
				System.out.println(element.getFileName());
				System.out.println(element.getHash());
				System.out.println(element.getHref());
				System.out.println(element.getDescription());
			}
		} else {
			if (uploader.getErrorResponse()!=null) {
				System.out.println(uploader.getErrorResponse().getStatus());
				System.out.println(uploader.getErrorResponse().getError());
				System.out.println(uploader.getErrorResponse().getErrorDescription());
			}
		}
	}

}
