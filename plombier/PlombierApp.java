/*
Rémy Lataste
2021 Projet INF1404
Main executable de l'application
*/
package plombier;


public class PlombierApp {
	static Fenetre fenetre;
	
	// x est j , y est i
	public static void main(String[] args) {
		
		fenetre = new Fenetre();
		
		Pile <Tuyau> Work = new Pile<Tuyau>();
		//Pile d'essai

		
		Tuyau coupPrecedent;
		Plateau plateau;
		Plateau platsave;
		plateau = new Plateau();
		platsave = new Plateau();
		
		//Si coordonnées source à -1 : pas de source setup.
		int isource = -1;
		int jsource = -1;
		
		
		//SET UNE SOURCE EN DUR :
		//isource = 5;
		//jsource = 5;
		//plateau.set(isource, jsource, 7); 
		//Initialisation de la source
		
		int bufftests;
		int tuyautot = 0;
		int action = 0;
		int ibuff;
		int jbuff;
		int bufftype;
		int tuyau=0;
		int tuyauaff = 0;
		int repete;
		int breakw = 0;
		int tuyautotsave = 0;
		int occupeesave = 64;

		//TUYAUX INITIAUX : VALEUR TYPE + 10
		
		//DOCUMENTATION : Tuyau(int i, int j, int output, int tests, int type){
		

		
		Work.Empile(new Tuyau(isource,jsource,0,1,7));
		coupPrecedent = Work.SommetPile();
		//while(coupPrecedent.typetestes != 99) {
		while(true) {
			breakw = 0;
			
			
			
		//Boucle sans lancement de l'algorithme
		while(fenetre.status() == 0) {
			//Verif buffer reset
			if (fenetre.reset == 1) {
				fenetre.reseted();
				plateau = new Plateau();
				breakw = 1;
				fenetre.reset = 0;
				fenetre.statusrst();
				isource = -1;
				jsource = -1;
				tuyautot = 0;
				tuyautotsave = 0;
				occupeesave = 0;
			}
			
			try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
			
			//Verif buffer tuyau à placer
			if(fenetre.ibuff() != -1) {
				//Si != -1, il y a un nouveau tuyau dans le buffer
				if (plateau.affichecoo(fenetre.ibuff(), fenetre.jbuff()) == 0) {
					//Cas pas de tuyau dans la case
					tuyau = plateau.affichecoo(fenetre.ibuff(), fenetre.jbuff()) + 11 ;
					tuyauaff = tuyau -10;
					tuyautot = tuyautot + 1;
					tuyautotsave = tuyautot;
				}
				if ((plateau.affichecoo(fenetre.ibuff(), fenetre.jbuff()) != 0) & (plateau.affichecoo(fenetre.ibuff(), fenetre.jbuff()) != 6) & (plateau.affichecoo(fenetre.ibuff(), fenetre.jbuff()) != 7) ) {
					//Cas tuyau suivant dans la case
					tuyau = plateau.affichecoo(fenetre.ibuff(), fenetre.jbuff()) + 1 ;
					tuyauaff = tuyau -10;
				}
				if (plateau.affichecoo(fenetre.ibuff(), fenetre.jbuff()) == 16){
					if(isource != -1) {
						//Cas placement d'une source
						tuyau = 0 ;
						tuyauaff = 7;
					}
					else {
						//Cas reset de la case
						tuyau = 7 ;
						tuyauaff = 0;
						isource = fenetre.ibuff();
						jsource = fenetre.jbuff();
						tuyautot = tuyautot - 1;
						tuyautotsave = tuyautot;
					}
					
					
				}
				if (plateau.affichecoo(fenetre.ibuff(), fenetre.jbuff()) == 7){
					tuyau = 0 ;
					tuyauaff = 7;
					isource = -1;
					jsource = -1;
				}
				plateau.set(fenetre.ibuff(), fenetre.jbuff(), tuyau );
				fenetre.btnimage(tuyauaff, fenetre.ibuff(), fenetre.jbuff());
				fenetre.place();
				
				
			}			
		}
		//Verif la présence d'une source avant le lancement de l'algo
		if(isource == -1) {
			fenetre.popup("Source non specifiée ! Arretez le programme et specifiez une source en cliquant 7 fois sur un bouton");
			breakw = 1;
			fenetre.statusrst();
		}
		
		//Tant que le meilleur chemin n'a pas été trouvé et que breakw n'est pas sur 1
		while(breakw == 0) {


			
			//Verif buffer bouton stop
			while(fenetre.status() == 0) {
				try {
	                Thread.sleep(1000);
	                if (fenetre.reset == 1) {
	                	fenetre.popup("Erreur : Impossible de reinitialiser quand le programme est sur pause");
	                	fenetre.reset = 0;
	                }
	                
	            } catch (InterruptedException ex) {
	            }
			}
			
			
			coupPrecedent = Work.SommetPile();
			
			/*
			======================OUTILS DE DEBUG============================
			System.out.print(" TYPE"+coupPrecedent.typetestes+" TYPE");
			System.out.print(" OUT"+coupPrecedent.outputPrecedent+" OUT");
			System.out.print(" X"+coupPrecedent.posPrecedentX+" X");
			System.out.print(" Y"+coupPrecedent.posPrecedentY+" Y");
			System.out.print("DEBUG");
			System.out.print(plateau.affichecoo(isource, jsource));
			=================================================================
			 */
			
			
			//SORTIE DE FONTAINE
			if(coupPrecedent.typePrecedent == 7) {
				if(coupPrecedent.typetestes == 1) {
					//On assimile la source a un tuyau vertical alimentant le nord
					Work.Depile();
					Work.Empile(new Tuyau(isource,jsource,0,2,7));
					Work.Empile(new Tuyau(isource,jsource,1,5,2));
					plateau.reset(isource, jsource);
					plateau.set(isource,jsource,2);
					fenetre.btnimage(0, isource, jsource);
					plateau.affiche();
					action = 1;
				}
				if(coupPrecedent.typetestes == 2) {
					//On assimile la source a un tuyau horizontal alimentant l'est
					Work.Depile();
					Work.Empile(new Tuyau(isource,jsource,0,3,7));
					Work.Empile(new Tuyau(isource,jsource,2,5,1));
					plateau.reset(isource, jsource);
					plateau.set(isource,jsource,1);
					plateau.affiche();
					action = 1;
				}
				if(coupPrecedent.typetestes == 3) {
					//On assimile la source a un tuyau vertical alimentant le sud
					Work.Depile();
					Work.Empile(new Tuyau(isource,jsource,0,4,7));
					Work.Empile(new Tuyau(isource,jsource,3,5,1));
					plateau.reset(isource, jsource);
					plateau.set(isource,jsource,2);
					plateau.affiche();
					action = 1;
				}
				if(coupPrecedent.typetestes == 4) {
					//On assimile la source a un tuyau horizontal alimentant l'ouest
					Work.Depile();
					Work.Empile(new Tuyau(isource,jsource,0,99,7));
					Work.Empile(new Tuyau(isource,jsource,4,5,2));
					plateau.reset(isource, jsource);
					plateau.set(isource,jsource,1);
					plateau.affiche();
					action = 1;
				}
			}

			//Remplissage sans backtrack
			if(coupPrecedent.outputPrecedent == 2) {
				//BUG DOUBLE PASSAGE : FIXE
				
				if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 1) {
					//Bloc suivant vertical
					Work.Empile(new Tuyau(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY + 1,2,8,1));
					plateau.set(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY + 1 ,1);
					fenetre.btnimage(1, coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY + 1);
					tuyautot = tuyautot - 1;
					plateau.affiche();
					action = 1;
				}
				
				
				if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 7) {
					//Bloc suivant vide
					//On met un droit puis on le changera avec le backtrack si besoin
					Work.Empile(new Tuyau(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY + 1,2,0,1));
					plateau.set(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY + 1,1);
					fenetre.btnimage(1, coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY + 1);
					plateau.affiche();
					action = 1;
				}
				
				if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 3) {
					//Bloc suivant coude 3
					Work.Empile(new Tuyau(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY + 1,3,8,3));
					plateau.set(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY + 1 ,3);
					fenetre.btnimage(3, coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY + 1);
					tuyautot = tuyautot - 1;
					plateau.affiche();
					action = 1;
				}
				if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 5) {
					//Bloc suivant coude 4
					Work.Empile(new Tuyau(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY + 1,1,8,5));
					plateau.set(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY + 1 ,5);
					fenetre.btnimage(5, coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY + 1);
					tuyautot = tuyautot - 1;
					plateau.affiche();
					action = 1;
				}
			}
		if(coupPrecedent.outputPrecedent == 1) {
			//BUG DOUBLE PASSAGE : FIXE
			
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 2) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX -1,coupPrecedent.posPrecedentY,1,8,2));
				plateau.set(coupPrecedent.posPrecedentX -1,coupPrecedent.posPrecedentY,2);
				fenetre.btnimage(2, coupPrecedent.posPrecedentX - 1, coupPrecedent.posPrecedentY);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
			
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 7) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX - 1,coupPrecedent.posPrecedentY,1,0,2));
				plateau.set(coupPrecedent.posPrecedentX - 1,coupPrecedent.posPrecedentY,2);
				fenetre.btnimage(2, coupPrecedent.posPrecedentX - 1, coupPrecedent.posPrecedentY);
				plateau.affiche();
				action = 1;
			}
		
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 3) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX -1,coupPrecedent.posPrecedentY,4,8,3));
				plateau.set(coupPrecedent.posPrecedentX -1,coupPrecedent.posPrecedentY,3);
				fenetre.btnimage(3, coupPrecedent.posPrecedentX - 1, coupPrecedent.posPrecedentY);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 4) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX -1,coupPrecedent.posPrecedentY,2,8,4));
				plateau.set(coupPrecedent.posPrecedentX -1,coupPrecedent.posPrecedentY,4);
				fenetre.btnimage(4, coupPrecedent.posPrecedentX - 1, coupPrecedent.posPrecedentY);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
	
			}

		
		
		if(coupPrecedent.outputPrecedent == 3) {
			//BUG DOUBLE PASSAGE : FIXE
			
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 2) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX+1,coupPrecedent.posPrecedentY,3,8,2));
				plateau.set(coupPrecedent.posPrecedentX+1,coupPrecedent.posPrecedentY,2);
				fenetre.btnimage(2, coupPrecedent.posPrecedentX+1, coupPrecedent.posPrecedentY);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
			
			
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 7) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX+1,coupPrecedent.posPrecedentY,3,0,2));
				plateau.set(coupPrecedent.posPrecedentX+1,coupPrecedent.posPrecedentY,2);
				fenetre.btnimage(2, coupPrecedent.posPrecedentX+1, coupPrecedent.posPrecedentY);
				plateau.affiche();
				action = 1;
			}
			
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 5) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX+1,coupPrecedent.posPrecedentY,4,8,5));
				plateau.set(coupPrecedent.posPrecedentX+1,coupPrecedent.posPrecedentY,5);
				fenetre.btnimage(5, coupPrecedent.posPrecedentX+1, coupPrecedent.posPrecedentY);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 6) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX+1,coupPrecedent.posPrecedentY,2,8,6));
				plateau.set(coupPrecedent.posPrecedentX+1, coupPrecedent.posPrecedentY,6);
				fenetre.btnimage(6, coupPrecedent.posPrecedentX+1, coupPrecedent.posPrecedentY);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
	
			}
		if(coupPrecedent.outputPrecedent == 4) {
			//BUG DOUBLE PASSAGE : FIXE
			
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 1) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY-1,4,8,1));
				plateau.set(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY-1,1);
				fenetre.btnimage(1, coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY-1);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
			
			
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 7) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY-1,4,0,1));
				plateau.set(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY-1,1);
				fenetre.btnimage(1, coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY-1);
				plateau.affiche();
				action = 1;
			}
			

			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 4) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY-1,3,8,4));
				plateau.set(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY-1,4);
				fenetre.btnimage(4, coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY-1);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
			if(plateau.choixSuivantPossible(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY, coupPrecedent.outputPrecedent) == 6) {
				Work.Empile(new Tuyau(coupPrecedent.posPrecedentX,coupPrecedent.posPrecedentY-1,1,8,6));
				plateau.set(coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY-1,6);
				fenetre.btnimage(6, coupPrecedent.posPrecedentX, coupPrecedent.posPrecedentY-1);
				tuyautot = tuyautot - 1;
				plateau.affiche();
				action = 1;
			}
		
			}

			//=======================================Backtrack=====================================
			//Si pas d'action possible : backtrack
			
		if(action ==0) {
				//backtrack
				repete = 1;
				while(repete == 1)
				{
					repete = 0;
				while(coupPrecedent.typetestes == 8) {
					//double backtrack, car backtrack sur un tuyau fixe, il faudra aussi le replacer
					bufftype = coupPrecedent.typePrecedent;
					ibuff = coupPrecedent.posPrecedentX;
					jbuff = coupPrecedent.posPrecedentY;
					tuyautot = tuyautot + 1;
					

					Work.Depile();
					plateau.set(ibuff,jbuff,bufftype + 10);
					
					
					
					
					coupPrecedent = Work.SommetPile();
					
				}
				
				if(coupPrecedent.typetestes == 0) {
					bufftests = coupPrecedent.typetestes;
					ibuff = coupPrecedent.posPrecedentX;
					jbuff = coupPrecedent.posPrecedentY;
					
					Work.Depile();
					coupPrecedent = Work.SommetPile();
					if(coupPrecedent.outputPrecedent == 1) {
						Work.Empile(new Tuyau(ibuff,jbuff,4,1,3));
						plateau.reset(ibuff, jbuff);
						plateau.set(ibuff,jbuff,3);
						fenetre.btnimage(3, ibuff, jbuff);
						plateau.affiche();
					}
					else if(coupPrecedent.outputPrecedent == 2) {
						Work.Empile(new Tuyau(ibuff,jbuff,3,1,3));
						plateau.reset(ibuff, jbuff);
						plateau.set(ibuff,jbuff,3);
						fenetre.btnimage(3, ibuff, jbuff);
						plateau.affiche();
					}
					else if(coupPrecedent.outputPrecedent == 3) {
						Work.Empile(new Tuyau(ibuff,jbuff,4,1,5));
						plateau.reset(ibuff, jbuff);
						plateau.set(ibuff,jbuff,5);
						fenetre.btnimage(5, ibuff, jbuff);
						plateau.affiche();
					}
					else if(coupPrecedent.outputPrecedent == 4) {
						Work.Empile(new Tuyau(ibuff,jbuff,3,1,4));
						plateau.reset(ibuff, jbuff);
						plateau.set(ibuff,jbuff,4);
						fenetre.btnimage(4, ibuff, jbuff);
						plateau.affiche();
					}
					else {
						fenetre.popup("ERROR T0");
				}

				}
				else if(coupPrecedent.typetestes == 1) {
					bufftests = coupPrecedent.typetestes;
					ibuff = coupPrecedent.posPrecedentX;
					jbuff = coupPrecedent.posPrecedentY;
					
					Work.Depile();
					
					
					coupPrecedent = Work.SommetPile();
					if(coupPrecedent.outputPrecedent == 1) {
						Work.Empile(new Tuyau(ibuff,jbuff,2,2,4));
						plateau.reset(ibuff, jbuff);
						plateau.set(ibuff,jbuff,4);
						fenetre.btnimage(4, ibuff, jbuff);
						plateau.affiche();

					}
					else if(coupPrecedent.outputPrecedent == 2) {
						Work.Empile(new Tuyau(ibuff,jbuff,1,2,5));
						plateau.reset(ibuff, jbuff);
						plateau.set(ibuff,jbuff,5);
						fenetre.btnimage(5, ibuff, jbuff);
						plateau.affiche();
					}
					else if(coupPrecedent.outputPrecedent == 3) {
						Work.Empile(new Tuyau(ibuff,jbuff,2,2,6));
						plateau.reset(ibuff, jbuff);
						plateau.set(ibuff,jbuff,6);
						fenetre.btnimage(6, ibuff, jbuff);
						plateau.affiche();
					}
					else if(coupPrecedent.outputPrecedent == 4) {
						Work.Empile(new Tuyau(ibuff,jbuff,1,2,6));
						plateau.reset(ibuff, jbuff);
						plateau.set(ibuff,jbuff,6);
						fenetre.btnimage(6, ibuff, jbuff);
						plateau.affiche();
					}
					else {
							fenetre.popup("ERROR T1");
					}
					
				}
				else if(coupPrecedent.typetestes == 2) {
					int boucle = 0;
					while(boucle == 0) {
					boucle = 1;
					//double backtrack et suivant du précedent
					ibuff = coupPrecedent.posPrecedentX;
					jbuff = coupPrecedent.posPrecedentY;
					
					
					
					Work.Depile();
					
					
					
					plateau.reset(ibuff, jbuff);
					fenetre.btnimage(7, ibuff, jbuff);
					coupPrecedent = Work.SommetPile();
					repete = 1;
					/*
					if(coupPrecedent.typetestes == 0) {
						bufftests = coupPrecedent.typetestes;
						ibuff = coupPrecedent.posPrecedentX;
						jbuff = coupPrecedent.posPrecedentY;
						
						Work.Depile();
						coupPrecedent = Work.SommetPile();
						if(coupPrecedent.outputPrecedent == 1) {
							Work.Empile(new Tuyau(ibuff,jbuff,4,1,3));
							plateau.reset(ibuff, jbuff);
							plateau.set(ibuff,jbuff,3);
							fenetre.btnimage(3, ibuff, jbuff);
							plateau.affiche();
						}
						else if(coupPrecedent.outputPrecedent == 2) {
							Work.Empile(new Tuyau(ibuff,jbuff,3,1,3));
							plateau.reset(ibuff, jbuff);
							plateau.set(ibuff,jbuff,3);
							fenetre.btnimage(3, ibuff, jbuff);
							plateau.affiche();
						}
						else if(coupPrecedent.outputPrecedent == 3) {
							Work.Empile(new Tuyau(ibuff,jbuff,4,1,5));
							plateau.reset(ibuff, jbuff);
							plateau.set(ibuff,jbuff,5);
							fenetre.btnimage(5, ibuff, jbuff);
							plateau.affiche();
						}
						else if(coupPrecedent.outputPrecedent == 4) {
							Work.Empile(new Tuyau(ibuff,jbuff,3,1,4));
							plateau.reset(ibuff, jbuff);
							plateau.set(ibuff,jbuff,4);
							fenetre.btnimage(4, ibuff, jbuff);
							plateau.affiche();
						}
						else {
							fenetre.popup("ERROR T2-1");
						}

					}
					else if(coupPrecedent.typetestes == 1) {
						bufftests = coupPrecedent.typetestes;
						ibuff = coupPrecedent.posPrecedentX;
						jbuff = coupPrecedent.posPrecedentY;
						
						Work.Depile();
						coupPrecedent = Work.SommetPile();
						if(coupPrecedent.outputPrecedent == 1) {
							Work.Empile(new Tuyau(ibuff,jbuff,2,2,4));
							plateau.reset(ibuff, jbuff);
							plateau.set(ibuff,jbuff,4);
							fenetre.btnimage(4, ibuff, jbuff);
							plateau.affiche();
						}
						else if(coupPrecedent.outputPrecedent == 2) {
							Work.Empile(new Tuyau(ibuff,jbuff,1,2,5));
							plateau.reset(ibuff, jbuff);
							plateau.set(ibuff,jbuff,5);
							fenetre.btnimage(5, ibuff, jbuff);
							plateau.affiche();
						}
						else if(coupPrecedent.outputPrecedent == 3) {
							Work.Empile(new Tuyau(ibuff,jbuff,2,2,6));
							plateau.reset(ibuff, jbuff);
							plateau.set(ibuff,jbuff,6);
							fenetre.btnimage(6, ibuff, jbuff);
							plateau.affiche();
						}
						else if(coupPrecedent.outputPrecedent == 4) {
							Work.Empile(new Tuyau(ibuff,jbuff,1,2,6));
							plateau.reset(ibuff, jbuff);
							plateau.set(ibuff,jbuff,6);
							fenetre.btnimage(6, ibuff, jbuff);
							plateau.affiche();
						}
						else {
							fenetre.popup("ERROR T2-2");
						}
						
					}
					else if(coupPrecedent.typetestes == 2) {
					boucle = 0;	
					coupPrecedent = Work.SommetPile();
					}
					
					if(coupPrecedent.typetestes == 8) {
						//double backtrack, car backtrack sur un tuyau fixe, il faudra aussi le replacer
						bufftype = coupPrecedent.typePrecedent;
						ibuff = coupPrecedent.posPrecedentX;
						jbuff = coupPrecedent.posPrecedentY;
						tuyautot = tuyautot + 1;
						Work.Depile();
						plateau.set(ibuff,jbuff,bufftype + 10);
						coupPrecedent = Work.SommetPile();

							repete = 1;

					}*/
					}
		}
				else if(coupPrecedent.typetestes == 5) {
					//Cas tuyau doublon fontaine, on dépile juste, le type suivant a tester est sauvegardé dans la fontaine
					Work.Depile();
					plateau.affiche();
				}
			}
		}
			//=======================================Sauvegarde du meilleur chemin=====================================
			//Enregistrement dans saved si passage par plus de tuyau initiaux et ou plus de casse et meme nombre de tuyau initiaux
			
			if(tuyautot < tuyautotsave) {
				tuyautotsave = tuyautot;
				occupeesave = plateau.libre();
				int i,j;
				for (i=1;i<9;i++){
					for (j=1; j<9;j++){
						platsave.plateau [i][j] = plateau.plateau [i][j];
					}
				}				
			}
			if((plateau.libre() < occupeesave)&(tuyautot == tuyautotsave)) {
				tuyautotsave = tuyautot;
				occupeesave = plateau.libre();	
				int i,j;
				for (i=1;i<9;i++){
					for (j=1; j<9;j++){
						platsave.plateau [i][j] = plateau.plateau [i][j];
					}
				}
			}
			
			
			//=======================================Variables=====================================
			action = 0;
			
			//Si toutes les positions ont étées testées ou 
			if((coupPrecedent.typetestes == 99)||(plateau.libre() == 0 & tuyautot == 0) ) {
				breakw = 1;
				fenetre.popup("Meilleur chemin trouvé !");
				fenetre.status = 0;
				int i,j;
				for (i=0;i<8;i++){
					for (j=0; j<8;j++){
						fenetre.btnimage(platsave.affichecoo(i, j),i,j);
					}
				}
				fenetre.btnimage(0,isource,jsource);
			}
			
			//Verif buffer bouton reset
			if (fenetre.reset == 1) {
				fenetre.reseted();
				plateau = new Plateau();
				breakw = 1;
				fenetre.reset = 0;
				fenetre.statusrst();
				isource = -1;
				jsource = -1;
				Work = new Pile<Tuyau>();
				tuyautot = 0;
				tuyautotsave = 0;
				occupeesave = 0;
			}
			
		}	
	
	}
}

}
