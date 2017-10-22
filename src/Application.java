
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import csp.*;
import java.util.Scanner;
import structure.*;

public class Application{
	public static void main(String [] args) throws Exception{
		
		/**
		 * Pour résoudre un problème à base de contrainte
		 */

		String nomF = "example/contrainte/sudoku4x4.txt";
		System.out.println("Chargement du fichier : " + new java.io.File(".").getCanonicalPath() + "/" + nomF);
		BufferedReader lectureF = new BufferedReader(new FileReader(nomF));
		Reseau monCSP = new Reseau(lectureF);
		System.out.println("\nMon réseau de contraintes :\n" + monCSP);
		
		CSP res = new CSP(monCSP);
		res.searchSolution();		
		//res.searchAllSolutions();

		
		/**
		 * Pour résoudre un problème à base de règles : 
		 */
		
		/*
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Rentrez le nom du fichier à charger :");
		String str = sc.nextLine();
		KnowledgeBase kb = new KnowledgeBase(str);

		str = "";

		while(!str.equals("exit")){
			System.out.println("Veuillez rentrer une requête :");
			str = sc.nextLine();
			kb.requete(str);
		}
		
		sc.close();
		System.out.println("Bonne journée :)");
		
		*/
		
		KnowledgeBase kb = new KnowledgeBase("example/regle/annexe.txt");
		System.out.println("Affichage de la base de connaissance annexe: ");
		System.out.println(kb);
		
		System.out.println("Saturation de la base :");
		kb.saturerFaits();
		
		System.out.println("Affichage de la base de connaissance saturé");
		System.out.println(kb);
		
		System.out.println("\nQuels sont les animaux qui boivent quelque chose ?");
		kb.requete("Animal(x);Boire(x,y)");
		
		System.out.println("\nQuels sont les animaux qui consomment de l'eau ?");
		kb.requete("Animal(x);Consomme(x,'eau')");
		
		System.out.println("\nQuels sont les animaux carnivores qui mangent un herbivore ?");
		kb.requete("Animal(x);Carnivore(x);Animal(y);Herbivore(y);Mange(x,y)");
		
		System.out.println("\nQuels sont les animaux herbivores qui mangent un carnivore ?");
		kb.requete("Animal(x);Herbivore(x);Animal(y);Carnivore(y);Mange(x,y)");
		
		
		/*
		KnowledgeBase kb = new KnowledgeBase("example/regle/oedipe.txt");
		System.out.println(kb);
		
		System.out.println("Saturation de la base :");
		kb.saturerFaits();
		
		System.out.println("Affichage de la base de connaissance saturé");
		System.out.println(kb);
		
		System.out.println("\nTrouver tous les x et y tel que : aMere(x,y);h(x) ?");
		kb.requete("aMere(x,y);h(x)");
		
		System.out.println("\nTrouver tous les x et y tel que : aMere(x,y);traumatise(x);f(x) ?");
		kb.requete("aMere(x,y);traumatise(x);f(x)");
		
		System.out.println("\nA-t-on traumatise('A') ?");
		kb.requete("traumatise('A')");
		
		*/
		
	}
}