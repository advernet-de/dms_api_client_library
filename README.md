# Advernet.de DMS API Client Library for Java

*Advernet.de DMS API Client Library for Java* ist eine Programmbibliothek
zum Zugriff auf die REST-API einer Instanz des *Advernet.de DMS*.

*Advernet.de DMS API Client Library for Java* ist in Java programmiert
und kann unter den Bedingungen der *MIT License* genutzt werden.

Die Library ist in Entwicklung und implementiert in der jetzigen
Version eine Untermenge der API-Funktionen. Bis zum vollständigen 
Release 1.0 kann sich die Schnittstelle jederzeit ohne Ankündigung ändern.

## Build

Die folgenden Schritte beschreiben als Beispiel die Installation
und den Build unter Debian 9.

* installieren Sie folgende Pakete, wenn noch nicht vorhanden:
    * openjdk-8-jdk
    * ant
    * libgoogle-gson-java
* Download oder Klonen des Repositories
* Projekt-Build (Befehl *ant* im Repository-Verzeichnis aufrufen)

Sollte *gson.jar* nicht unter */usr/share/java* zu finden sein, muss es im Classpath
angegeben werden.

## Beispiele

Starten Sie aus dem Repository-Verzeichnis einen der folgenden Befehle:

~~~
java -cp dms-api-client-library.jar:build de.advernet.api.client.examples.AccessTokenRequestExample
~~~

~~~
java -cp dms-api-client-library.jar:build de.advernet.api.client.examples.PermissionRequestExample
~~~

~~~
java -cp dms-api-client-library.jar:build de.advernet.api.client.examples.DocumentUploadExample
~~~

~~~
java -cp dms-api-client-library.jar:build de.advernet.api.client.examples.DocumentInfoRequestExample
~~~

~~~
java -cp dms-api-client-library.jar:build de.advernet.api.client.examples.DocumentSearchExample
~~~

## API-Referenz

Eine Referenz der REST-API des *Advernet.de DMS* finden Sie unter <https://www.advernet.de/api-referenz.html>.

## Kontakt

Bei Fragen und Anmerkungen zur Library nehmen Sie bitte Kontakt zu uns auf!

~~~
Advernet.de GmbH
Bergstedter Chaussee 42
22395 Hamburg

+49 40 228692950
info@advernet.de
www.advernet.de

Geschäftsführer
Dipl.-Inform. Gabriel Aleanakian

Handelsregister
Amtsgericht Hamburg HRB 134495
~~~
