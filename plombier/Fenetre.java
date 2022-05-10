/*
Rémy Lataste
2021 Projet INF1404
Affichage front end de la fenetre + gestion des boutons
*/
package plombier;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*; 



public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;  
	int i;
	int j;
	JButton[][] array = new JButton[8][8];
	//on utilise un tableau de boutons pour identifier indépendament chaque bouton. Ex le bouton array[1][2] qui a pour i 1 et j 2
	
	Icon source = new ImageIcon("src\\plombier\\source.PNG");
	Icon icon1 = new ImageIcon("src\\plombier\\imag1.PNG");
	Icon icon2 = new ImageIcon("src\\plombier\\imag2.PNG");
	Icon icon3 = new ImageIcon("src\\plombier\\imag3.PNG");
	Icon icon4 = new ImageIcon("src\\plombier\\imag4.PNG");
	Icon icon5 = new ImageIcon("src\\plombier\\imag5.PNG");
	Icon icon6 = new ImageIcon("src\\plombier\\imag6.PNG");
	Icon none = new ImageIcon("src\\plombier\\none.PNG");
	//Initialisation des icones utilisées par le logiciel
	
	
	public int status = 0;
	public int ibuff = -1;
	public int jbuff = -1;
	int reset = 0;
	//Buffers à lire par le main
	
	
	
	public Fenetre(){
	    
		
		//Au clic sur un btn on retourne ses coordonnées
				Action clic = new AbstractAction(){
			        @Override
			        public void actionPerformed(ActionEvent e){
			        	for (int i = 0; i < 8; ++i) {
					    	for (int j = 0; j < 8; ++j) {
					    		if(e.getSource() == array[i][j]){
					    			ibuff = i;
					    			jbuff = j;
					    		}
					    		
					    	}
			        	}

			        }
			    };
		
		//Lorsque l'on presse les boutons on set un buffer pour récuperer dans le main l'information
		Action start = new AbstractAction(){
	        @Override
	        public void actionPerformed(ActionEvent e){
	        		status = 1;
	        }
	    };
		
	    Action stop = new AbstractAction(){
	        @Override
	        public void actionPerformed(ActionEvent e){
	        		status = 0;

	        }
	    };
	    
	   Action Reset = new AbstractAction(){
	        @Override
	        public void actionPerformed(ActionEvent e){
	        		reset = 1;

	        }
	    };
	    
	    
	    //Definition de la toolbar
	    JToolBar tb = new JToolBar();
	    JButton Demarrer = new JButton("Démarrer");
        JButton Arreter = new JButton("Arrêter");
        JButton REset = new JButton("Reinitialiser");
        JPanel p = new JPanel();
        JPanel tuyau = new JPanel();
        p.add(Demarrer);
        p.add(Arreter);
        //p.add(REset);
        tb.add(p);
	    this.add(tb);
	    
	    //Definition du conteneur qui permet de séparer en deux panels la toolbar et le tableau
	    JPanel contentPane= new JPanel();
	    contentPane.setLayout(new BorderLayout());
	    contentPane.add(tb, BorderLayout.NORTH);
	    contentPane.add(tuyau, BorderLayout.CENTER);
	    this.add(contentPane);
	    
	    //Initialisation des listeners d'evenements
		Demarrer.addActionListener(start);
		Arreter.addActionListener(stop);
		REset.addActionListener(Reset);
	    
		//Caractéristiques de la fenetre
		this.setTitle("Jeu du plombier");
	    this.setSize(800, 850);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    
	    
	    //On définit le layout à utiliser sur le content pane
	    //Trois lignes sur deux colonnes
	    tuyau.setLayout(new GridLayout(8, 8));
	    //On crée et ajoute les bouton au content pane de la JFrame
	    for (int i = 0; i < 8; ++i) {
	    	for (int j = 0; j < 8; ++j) {
	    	JButton b = new JButton();
	    	array[i][j] = b;
	    	array[i][j].addActionListener(clic);
	    	array[i][j].setIcon(none);
	    	tuyau.add(b);
	    	}
	    	}
	    this.setVisible(true);
	  }      

	void btnimage(int id, int x, int y) {
		if(id == 0) {
			array[x][y].setIcon(source);
		}
		if(id == 1) {
			array[x][y].setIcon(icon1);
		}
		if(id == 2) {
			array[x][y].setIcon(icon2);
		}
		if(id == 3) {
			array[x][y].setIcon(icon3);
		}
		if(id == 4) {
			array[x][y].setIcon(icon4);
		}
		if(id == 5) {
			array[x][y].setIcon(icon5);
		}
		if(id == 6) {
			array[x][y].setIcon(icon6);
		}
		if(id == 7) {
			array[x][y].setIcon(none);
		}
	}
	
	int status() {
		return status;
	}
	
	//A appeler lors d'erreurs au lancement pour annuler le demarrage du programme, ex :  pas de source definie
	void statusrst() {
		status = 0;
	}
	
	int ibuff() {
		return ibuff;
	}
	
	int jbuff() {
		return jbuff;
	}
	
	//A appeler lors du placement d'un element fixe pour reset le buffer
	void place() {
		jbuff = -1;
		ibuff = -1;
	}

	//Fonction a appeler lorsque l'on reset le plateau pour reset l'affichage
	void reseted() {
		reset = 0;
		for (int i = 0; i < 8; ++i) {
	    	for (int j = 0; j < 8; ++j) {
	    		array[i][j].setIcon(none);
	    	}
    	}
		
		
	}
	
	//Fonction pour ouvrir une pop up avec un message en argument
	void popup(String message) {
		JFrame parent = new JFrame();
		JOptionPane.showMessageDialog(parent, message);
	}
}
	
	