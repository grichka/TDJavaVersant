TDJavaVersant
=============

Projet en Java utilisant les technologies Versant et DB4O. Ce projet a pour but la prise en main des technologies de persistance objet Versant et DB4O.

Installation/Lancement
======================
Windows :
	se placer dans le dossier du projet avec cmd.
	executer :
		$> chcp 65001 (passage de la console en UTF-8)
		$> ant
	executer l'un des fichier .bat
	pour peupler une base versant (nom : versantDB)
		$> peupler-versant.bat 
	pour peupler une base DB4o
		$> peupler-db4o.bat
	pour lancer la console avec la base versant
		$> run-with-versant.bat
	pour lancer la console avec la base db4o
		$> run-with-versant.bat

Systême UNIX :
	se placer dans le dossier du projet avec un terminal.
	executer :
		$> ant
	executer l'un des fichier .sh
	pour peupler une base versant (nom : versantDB)
		$> peupler-versant.sh
	pour peupler une base DB4o
		$> peupler-db4o.sh
	pour lancer la console avec la base versant
		$> run-with-versant.sh
	pour lancer la console avec la base db4o
		$> run-with-versant.sh

Remarque : il peut y avoir des problèmes liés aux droits d'execution ; utiliser $> chmod +x fichier
		