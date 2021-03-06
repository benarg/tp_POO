# Ensimag 2A POO - TP 2016/17
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: strategieElem

testInvader:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestInvader.java

testLecture:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestLecteurDonnees.java

testSimulateur:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestSimulateur.java

testStrategieElem:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestStrategieElem.java

testStrategieEvolue:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestStrategieEvolue.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:bin/gui.jar TestStrategieElem cartes/carteSujet.map
# ou bien lancer l'execution en passant par ce Makefile:
#   > make strategieElem CARTE=carteSujet

CARTE = carteSujet

exeInvader: 
	java -classpath bin:bin/gui.jar TestInvader

exeLecture: 
	java -classpath bin TestLecteurDonnees cartes/carteSujet.map
 
exeSimulateur: testSimulateur
	java -classpath bin:bin/gui.jar TestSimulateur cartes/carteSujet.map

strategieElem: testStrategieElem
	java -classpath bin:bin/gui.jar TestStrategieElem cartes/$(CARTE).map

strategieEvolue: testStrategieEvolue
	java -classpath bin:bin/gui.jar TestStrategieEvolue cartes/$(CARTE).map

clean:
	rm -rf bin/*.class bin/outilsBase/*.class bin/robots/*.class bin/calculPCC/*.class bin/evenements/*.class bin/io/*.class bin/simulationTests/*.class bin/strategies/*.class
