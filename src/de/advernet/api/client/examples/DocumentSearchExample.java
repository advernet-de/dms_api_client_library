/**

Copyright (C) Advernet.de GmbH

 */

package de.advernet.api.client.examples;

import java.io.IOException;
import java.util.Scanner;

import de.advernet.api.client.DocumentInfo;
import de.advernet.api.client.DocumentSearcher;

public class DocumentSearchExample {

	public static void main(String[] args) throws IOException {
		
		DocumentSearcher requester = new DocumentSearcher();

		Scanner scanner = new Scanner(System.in);

		System.out.print("url_string:    ");
		requester.setUrlString(scanner.nextLine());

		System.out.print("access_token:  ");
		requester.setToken(scanner.nextLine());

		System.out.print("team:          ");
		requester.setTeam(scanner.nextLine());

		System.out.print("box:           ");
		requester.setBox(scanner.nextLine());

		System.out.print("date:          ");
		requester.setDate(scanner.nextLine());

		System.out.print("date_from:     ");
		requester.setDateFrom(scanner.nextLine());

		System.out.print("date_to:       ");
		requester.setDateTo(scanner.nextLine());

		System.out.print("document_type: ");
		requester.setDocumentType(scanner.nextLine());

		System.out.println("EOF-Markierung beendet die Eingabe von Schlagworten.");

		System.out.print("tag:           ");
		while (scanner.hasNextLine()) {
			requester.addTag(scanner.nextLine());
			System.out.print("tag:           ");
		}

		scanner.close();

		requester.searchDocuments();

		System.out.println();
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
