/*
Rémy Lataste
2021 Projet INF1404
Gestion et stockage du plateau
*/
package plombier;

public class Plateau {

	public int [][] plateau;


	Plateau(){
		//On rempli un tableau de 10x10 de 0 puis on remplace ses bordures par des nombres negatifs.
		//Initialement, les bordures etaient set à -1, maintenant elles sont à -i et -2j pour aider au debug (resolution de problemes de coordonnées)
		
		
		int i,j;
		plateau = new int [10][10] ;


		for (i=1;i<9;i++){
			for (j=1; j<9;j++){
				plateau [i][j] =0;
			}
		}

		for (j=0;j<10;j++){plateau [0][j] =-j*2;plateau [9][j] =-j*2;}
		for (i=0;i<10;i++){plateau [i][0] =-i;;plateau [i][9] =-i;}

		// affichage à la construction

		for (i=0;i<10;i++){
			for (j=0; j<10;j++){
				if (plateau [i][j]==0)
					System.out.print(" "+plateau [i][j]+" ");
				else System.out.print(plateau [i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	void set(int i, int j, int val){
		plateau[i+1][j+1] = val;

	}
	
	void reset (int i, int j){
		plateau[i+1][j+1] = 0;
	}

	void affiche(){
		int i,j;
		for (i=1;i<9;i++){
			for (j=1; j<9;j++){
				if (plateau [i][j]<9)
				System.out.print(" "+plateau [i][j]+" ");
				else System.out.print(plateau [i][j]+" ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
	
	int libre(){
		int i,j;
		int tot = 0;
		for (i=1;i<9;i++){
			for (j=1; j<9;j++){
				if (plateau [i][j] == 0) {
				tot = tot +1;
				}
			}
		}
		return tot;
	}
	
	int affichecoo(int i, int j){
				return plateau [i+1][j+1];
	}
	
	
	int choixSuivantPossible(int ii, int jj, int outputPrecedent){
		//Verifie, en fonction de l'orientation de la sortie précedente, les choix possibles
		
		int i,j;
		i=ii+2; j=jj+2;
	if (outputPrecedent==0) {
			return 8;
		}
	if (outputPrecedent==1) {
		if (plateau [i-2][j-1] ==0) {return 7;}
		//Pas de tuyau à la sortie du précedent, donc liberté totale
		if (plateau [i-2][j-1] ==12) {return 2;}
		//Tuyau de type 2 fixe à la sortie, donc nous allons devoir passer dedans.
		if (plateau [i-2][j-1] ==13) {return 3;}
		if (plateau [i-2][j-1] ==14) {return 4;}
	}
	if (outputPrecedent==2){
		if (plateau [i-1][j] ==0) {return 7;}
		if (plateau [i-1][j] ==11) {return 1;}
		if (plateau [i-1][j] ==13) {return 3;}
		if (plateau [i-1][j] ==15) {return 5;}
					}
	if (outputPrecedent==3) {
		if (plateau [i][j-1] ==0) {return 7;}
		if (plateau [i][j-1] ==12) {return 2;}
		if (plateau [i][j-1] ==15) {return 5;}
		if (plateau [i][j-1] ==16) {return 6;}
				}
	if (outputPrecedent==4){
		if (plateau [i-1][j-2] ==0) {return 7;}
		if (plateau [i-1][j-2] ==11) {return 1;}
		if (plateau [i-1][j-2] ==14) {return 4;}
		if (plateau [i-1][j-2] ==16) {return 6;}
}
	return -1;
}
}