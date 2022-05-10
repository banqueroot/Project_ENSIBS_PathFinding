/*
R�my Lataste
2021 Projet INF1404
Empilement de donn�es
*/
package plombier;

import java.util.Vector;

public class Pile <T>{
	Vector <T> table;
	
	Pile (){
		table = new Vector<T>();
	}
	
	void Empile (T x){
		table.add(table.size(), x);
	}
	
	T SommetPile(){
		return table.elementAt(table.size()-1);
	}
	
	void Depile(){
		table.remove(table.size()-1);
	}
	
	int Taille() {
		return table.size();
	}

}