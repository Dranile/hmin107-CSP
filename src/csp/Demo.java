package csp;
import java.io.BufferedReader;
import java.io.FileReader;

public class Demo{
	public static void main(String [] args) throws Exception{
		String nom4reine = "4reines.txt";
		System.out.println("Problème des 4 reines");
		BufferedReader lecture4reine = new BufferedReader(new FileReader(nom4reine));
		Reseau CSP4reine = new Reseau(lecture4reine);
		CSP res4reine = new CSP(CSP4reine);
		res4reine.searchSolution();
		res4reine.searchAllSolutions();
		
		System.out.println("\n");
		
		String nomColoration = "Coloration.txt";
		//System.out.println("Chargement du fichier : " + new java.io.File(".").getCanonicalPath() + "/" + nomF);
		System.out.println("Problème de coloration");
		BufferedReader lectureColoration = new BufferedReader(new FileReader(nomColoration));
		Reseau CSPColoration = new Reseau(lectureColoration);
		CSP resColoration = new CSP(CSPColoration);
		resColoration.searchSolution();
		resColoration.searchAllSolutions();
		
		System.out.println("\n");
		
		String nomhomo = "homomorphisme.txt";
		//System.out.println("Chargement du fichier : " + new java.io.File(".").getCanonicalPath() + "/" + nomF);
		System.out.println("Problème d'homomorphisme");
		BufferedReader lecturehomo = new BufferedReader(new FileReader(nomhomo));
		Reseau CSPhomo = new Reseau(lecturehomo);
		CSP reshomo = new CSP(CSPhomo);
		reshomo.searchSolution();
		reshomo.searchAllSolutions();
		
		System.out.println("\n");
		
		String nom8reine = "8reinesExt.txt";
		//System.out.println("Chargement du fichier : " + new java.io.File(".").getCanonicalPath() + "/" + nomF);
		System.out.println("Problème des 8 reines");
		BufferedReader lecture8reine = new BufferedReader(new FileReader(nom8reine));
		Reseau CSP8reine = new Reseau(lecture8reine);
		CSP res8reine = new CSP(CSP8reine);
		res8reine.searchSolution();
		res8reine.searchAllSolutions();
		
		System.out.println("\n");
		
		String nomZebre = "zebre.txt";
		//System.out.println("Chargement du fichier : " + new java.io.File(".").getCanonicalPath() + "/" + nomF);
		System.out.println("Problème du zèbre");
		BufferedReader lectureZebre = new BufferedReader(new FileReader(nomZebre));
		Reseau CSPZebre = new Reseau(lectureZebre);
		CSP resZebre = new CSP(CSPZebre);
		resZebre.searchSolution();
		resZebre.searchAllSolutions();
		
		System.out.println("\n");
		
		
	}
}
