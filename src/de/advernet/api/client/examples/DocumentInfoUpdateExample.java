/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client.examples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.advernet.api.client.*;

public class DocumentInfoUpdateExample {

	public static void main(String[] args) throws IOException {

		DocumentInfoRequester requester = new DocumentInfoRequester();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Aktuelle Metadaten des Dokumentes lesen.");
		
		System.out.print("url_string:   ");
		requester.setUrlString(scanner.nextLine());

		System.out.print("access_token: ");
		requester.setToken(scanner.nextLine());

		System.out.print("team:         ");
		requester.setTeam(scanner.nextLine());

		System.out.print("box:          ");
		requester.setBox(scanner.nextLine());

		System.out.print("document_no:  ");
		requester.setDocumentNo(scanner.nextLine());
		
		requester.loadDocumentInfo();

		if (requester.getDocumentInfoList()!=null) {
			for (DocumentInfo info : requester.getDocumentInfoList().getDocuments()) {
				System.out.println();
				System.out.println("document_no:          " + info.getDocumentNo());
				System.out.println("document_type:        " + info.getDocumentType());
				System.out.println("document_date:        " + info.getDocumentDate());
				System.out.println("document_tags:        " + info.getDocumentTags().toString());
				System.out.println("document_stamp_text:  " + info.getDocumentStampText());
				System.out.println("document_stamp_color: " + info.getDocumentStampColor());
				System.out.println("document_is_locked:   " + info.getDocumentIsLocked());
				System.out.println("document_note:        " + info.getDocumentNote());
				System.out.println("file_name:            " + info.getFileName());
				System.out.println("file_size:            " + info.getFileSize());
				System.out.println("file_hash:            " + info.getFileHash());
				System.out.println("meta_hash:            " + info.getMetaHash());

				DocumentInfoUpdater updater = new DocumentInfoUpdater();
				
				updater.initDocumentInfoList(info.getMetaHash());
				
				System.out.println();
				System.out.println("Neue Metadaten eingeben.");
				System.out.println("Bei leerer Eingabe wird der alte Wert behalten.");
				System.out.println("Bei Eingabe von „---“ (drei Minuszeichen) wird der Wert gelöscht.");
				
				DocumentInfo update = requester.getDocumentInfoList().getDocuments().get(0);

				String value = "";
				
				System.out.print("document_type:        ");
				value = scanner.nextLine();
				if (value!=null && !value.isEmpty()) {
					if (value.equals("---")) value = null;
					update.setDocumentType(value);
				}

				System.out.print("document_date:        ");
				value = scanner.nextLine();
				if (value!=null && !value.isEmpty()) {
					if (value.equals("---")) value = null;
					update.setDocumentDate(value);
				}

				System.out.print("document_stamp_text:  ");
				value = scanner.nextLine();
				if (value!=null && !value.isEmpty()) {
					if (value.equals("---")) value = null;
					update.setDocumentStampText(value);
				}
				
				System.out.print("document_stamp_color: ");
				value = scanner.nextLine();
				if (value!=null && !value.isEmpty()) {
					if (value.equals("---")) value = null;
					update.setDocumentStampColor(value);
				}
				
				System.out.println("Bitte „true“ eingeben, wenn das Dokument gesperrt werden soll.");
				System.out.print("document_is_locked:   ");
				update.setDocumentIsLocked(scanner.nextLine().equals("true")?true:false);
				
				System.out.print("document_note:        ");
				value = scanner.nextLine();
				if (value!=null && !value.isEmpty()) {
					if (value.equals("---")) value = null;
					update.setDocumentNote(value);
				}
				
				System.out.print("Liste der Schlagworte ändern? (j/N) ");
				String changeTags = scanner.nextLine();
				if (changeTags.equals("j") || changeTags.equals("J")) {
					System.out.println("Neue Liste der Schlagworte eingeben.");
					System.out.println("EOF-Markierung beendet die Eingabe.");
					List<String> tags = new ArrayList<String>();
					System.out.print("tag:                  ");
					while (scanner.hasNextLine()) {
						tags.add(scanner.nextLine());
						System.out.print("tag:                  ");
					}
					update.setDocumentTags(tags);
				}
				
				scanner.close();
				
				updater.setUrlString(requester.getUrlString());
				updater.setToken(requester.getToken());
				updater.setTeam(requester.getTeam());
				updater.setBox(requester.getBox());
				updater.setDocumentNo(requester.getDocumentNo());
				
				updater.getDocumentInfoList().addDocumentInfo(update);
				
				updater.updateDocumentInfo();

				if (updater.getSuccess()!=null && updater.getSuccess().equals("true")) {
					System.out.println("Änderungen erfolgreich durchgeführt");
				} else {
					if (updater.getErrorResponse()!=null) {
						System.out.println(updater.getErrorResponse().getStatus());
						System.out.println(updater.getErrorResponse().getError());
						System.out.println(updater.getErrorResponse().getErrorDescription());
					}
				}
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
