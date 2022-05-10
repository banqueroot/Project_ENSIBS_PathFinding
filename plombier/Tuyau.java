/*
Rémy Lataste
2021 Projet INF1404
Type tuyau à empiler pour la sauvegarde du chemin parcouru
*/
package plombier;

public class Tuyau {
	
	int posPrecedentX;
	int posPrecedentY;
	int outputPrecedent;
	/*
	Sortie allimentée par le tuyau précedent
	1: Nord 
	2: Est 
	3: Sud 
	4: Ouest
	*/
	int typePrecedent;
	/*
	Type du dernier tuyau 
	1: Droit horizontal  ═
	2: Droit vertical   ║
	3: Coudé ╗
	4: Coudé ╔
	
	5: Coudé ╝
	6: Coudé ╚
	*/
	int typetestes;
	/*
	Type du dernier tuyau suivant testé
	0: Pas de tuyau suivant essayé
	1: horizontal
	2: coude 1 (gauche du output)
	3: Coude 2 (droite du output)

	*/
	Tuyau(int i, int j, int output, int tests, int type){
		posPrecedentX=i;
		posPrecedentY=j;
		outputPrecedent=output;
		typetestes = tests;
		typePrecedent = type;
		
	}
}