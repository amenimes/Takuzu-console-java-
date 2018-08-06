

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Tak{

	
	int[][] grille;

	

	 
	public Tak() {
		
		int[][] grille = new int [10][10] ;
		this.grille = grille;
	}

	/**
	  Constructeur Genere le Takuzu passe en parametre. 
	
	 */
	public Tak(int[][] grille) {
		this.grille = grille;
	}
	
	/**
	  Constructeur qui Genere le Takuzu vide
	  
	 */
	public Tak(int taileGrille) {
		int[][] grille = new int[taileGrille][taileGrille];
		
		for (int i = 0; i < taileGrille; i++) {
			int[] ligne = new int[taileGrille];
			for (int j = 0; j < ligne.length; j++) {
				Random r = new Random();
				int value = r.nextInt(3);
				ligne[j] = value;				
			}
			grille[i] = ligne;
		}
		
		this.grille = grille;
	}
	
	public int[][] getGrille() {
		return grille;
	}
	public void setGrille(int[][] grille) {
		this.grille = grille;
	}
	
	public String toString(){
		String s = "";
		for (int i = 0; i < grille.length; i++) {
			s += Arrays.toString(grille[i]);
			if(i != grille.length -1 ) s += "\n";
		}
		return s.replace(",", "").replace("2", "*").replace("[", "").replace("]", "");
	}
	
	
	public Tak duplique() {
		int[][] duplique = new int[this.grille.length][];
		for (int i = 0; i < duplique.length; i++) {
			duplique[i] = this.duplique().grille[i];
		}
		
		return new Tak(duplique);
	};
	public Tak resolution(){
		return resolution(this, 0, 0);
	}
	private Tak resolution(Tak tak, int ligne, int colone)
{	
		
		for (int i = 0; i < 2; i++) {
			if(this.grille[ligne][colone] == 2){ 											// Si la case est vide 
				tak.grille[ligne][colone] =0;
			}
			
/*			System.out.println(tak 
					+ "\t Travail sur L"+ ligne + " valide? " + tak.ligneValide(ligne) 
					+ "\t C"+ colone + " valide? " + tak.coloneValide(colone));*/
			
			if(colone < this.grille[ligne].length -1){										// Si l'on ne se trouve pas en fin de ligne, on passe a la colone suivante
				if(tak.ligneValide(ligne) && tak.coloneValide(colone)){
					Tak tmp = resolution(tak, ligne, colone +1);					// On passe a la resolution de la case suivante
					if(tmp != null && tmp.estValide()) return tmp;
				}
			} else if(ligne < this.grille.length -1){										//  si l'on est en fin de ligne on verifie la validité de l et col et l'unicite de ligne
				if(tak.ligneValide(ligne) && tak.coloneValide(colone) && tak.ligneEstUnique(ligne)){
					int l=ligne + 1 ;
					Tak tmp = resolution(tak,l,colone);
													// On passe a la resolution de la ligne suivante(ligne+1)
					if(tmp != null && tmp.estValide())
						return tmp;
				}
			}else {
				if(tak.estValide())
					return tak;
				return null;
			}
		}
		
		return null;
	}
	
	/**
	 Verifie la validite du Takuzu
	 */
	public boolean estValide(){
		// Pour chaque lignes
		for (int i = 0; i < grille.length; i++) {
			if(!ligneValide(i) || !ligneEstUnique(i))
				return false;
			// Pour chaque colone dans la ligne i
			for (int j = 0; j < grille[i].length; j++) {
				 if(!coloneValide(j) || !coloneEstUnique(j))
					 return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Verifier la validite de la ligne 
	 */
	public boolean ligneValide(int i){
		boolean coloneIncomplete = false; 	// Variable boolenne  indiquant si la ligne est completement remplis
		int nb_de_1 = 0;					// Nombre du num 1 de la ligne
		int nb_de_0 = 0;					// Nombre du num 0 de la ligne
		for (int j = 2; j < grille[i].length; j++) {


	if (j>2)	{
		
			// Si trois cases consecutives sont identiques (non vides)
 			if(grille[i][j-2] == grille[i][j-1] && grille[i][j-1] == grille[i][j] && grille[i][j] != -1)
				return false; // Le Takuzu est invalide
}
 			
 			if(grille[i][j] == 0) 
 				nb_de_0 ++;
 			else if(grille[i][j] == 1) 
 				nb_de_1 ++;
 			else 
 				coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
		}
		
		return (nb_de_1 == (grille.length / 2) && nb_de_0 == (grille.length / 2)) || (coloneIncomplete && nb_de_0 <= (grille.length / 2) && nb_de_1 <= (grille.length / 2));
	}
	
	public void aff ( ){
		for (int k = 1; k< grille.length; k++)
		for (int l = 1; l < grille.length; l++)
			if( this.grille[k][l]==2){
				
			System.out.println(("*")) ;
			}
			else{
		System.out.println(this.grille[k][l]);
		}
		System.out.println("\n");
	}

	public boolean coloneValide(int i){
		boolean coloneIncomplete = false; 	// Variable indiquant si la ligne est completement remplis, ou non
		int nb_de_1 = 0;					// Nombre de 1 de la ligne
		int nb_de_0 = 0;					// Nombre de 0 de la ligne
		
		if(grille[0][i] == 0) 
			nb_de_0 ++;
		else if(grille[0][i] == 1) 
			nb_de_1 ++;
		else 
			coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
		
		if(grille[1][i] == 0) 
			nb_de_0 ++;
		else if(grille[1][i] == 1) 
			nb_de_1 ++;
		else 
			coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
		
		// Pour chaque lignes
		for (int j = 2; j < grille.length; j++) {
			// Si trois cases consecutives sont identique et remplis
 			if(grille[j-2][i] == grille[j-1][i] && grille[j-1][i] == grille[j][i] && grille[j][i] != -1)
				return false; // Le Takuzu est invalide
 			
 			if(grille[j][i] == 0) 
 				nb_de_0 ++;
 			else if(grille[j][i] == 1) 
 				nb_de_1 ++;
 			else 
 				coloneIncomplete = true; 	// On definit coloneIncomplete a vrai
		}
				
		return nb_de_1 == (grille.length / 2) || (coloneIncomplete && nb_de_1 <= (grille.length / 2));
	}
	
	/**
	 Verifie qu'une ligne n'a pas de double dans le Takuzu
	 */
	public boolean ligneEstUnique(int j){
		// Pour chaque ligne
		for (int i = 0; i < j; i++) {
			// Si deux lignes sont identique
			if(Arrays.equals(this.grille[i], this.grille[j]))
				return false;
		}
		
		return true;
	}
	
	/**
	 Verifie qu'une colone est unique
	 
	 */
	public boolean coloneEstUnique(int j){
		// Pour chaque ligne
		for (int i = 0; i < j; i++) {
			// Si deux lignes sont identique
			if(Arrays.equals(this.grille[i], this.grille[j]))
				return false;
		}
		
		return true;
	}
	
	/**
	 * Generer un Takuzu partiellement pre-remplis
	 
	 */
	public Tak generate(float proportionPreRemplissage) {
		Random rand = new Random();
		int nb = 0;
		
		// Melange les lignes du Takuzu
		do{
			int line1 = rand.nextInt(this.grille.length);
			int line2 = rand.nextInt(this.grille.length);
			
			int[] tmp = grille[line1];
			grille[line1] = grille[line2];
			grille[line2] = tmp;
			
		}while(++nb < this.grille.length && !this.estValide());
		
		// Parcours des lignes de la grille 
		for (int i = 0; i < grille.length; i++) {
			// Parcours des colones de la grille 
			for (int j = 0; j < grille[i].length; j++) {
				// Si le nombre tiré aléatoirement est plus grand de proportionPreRemplissage
				if(rand.nextFloat() > proportionPreRemplissage)
					this.grille[i][j] = 2; // La case est "vidé"
			}
		}
		
		return this;
	}
	
}
