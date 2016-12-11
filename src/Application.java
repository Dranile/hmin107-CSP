
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import csp.*;
import java.util.Scanner;
import structure.*;

public class Application{
	public static void main(String [] args) throws Exception{
		//String nomF = "premierCSP.txt";
		//String nomF = "deuxiemeCSP.txt";
		//String nomF = "troisiemeCSP.txt";
		//String nomF = "CSP/example.txt";
		//String nomF = "sudoku4x4.txt";
		//String nomF = "CSPTestIntention.txt";
		//System.out.println("Chargement du fichier : " + new java.io.File(".").getCanonicalPath() + "/" + nomF);
		//BufferedReader lectureF = new BufferedReader(new FileReader(nomF));
		//Reseau monCSP = new Reseau(lectureF);
		//System.out.println("\nMon réseau de contraintes :\n" + monCSP);
		//				
		//CSP res = new CSP(monCSP);
		//		
		// res.searchSolution();
		//		
		//res.searchAllSolutions();

		//System.out.println("Creation d'un factBase");
		//FactBase f = new FactBase("p('a','b');q('a',b','c');p('b','c');q('b','c','c')");
		//System.out.println(f.toString());
		//Homomorphisms h = new Homomorphisms("p(x,y);q(x,y,z)", f);

//		FactBase f = new FactBase("p('a','b');q('b','a');p('b','c');q('b','c')");
//		Homomorphisms h = new Homomorphisms("p(x,y);q(y,z)", f);
//		System.out.println(h);
//
//
//
//		Rule r = new Rule("p(x,y);p(y,z);p(x,z)");
//		System.out.println(r);
		
            /*
		KnowledgeBase kb = new KnowledgeBase("exemple/premierTest.txt");
                
                
                //Le loup mange-t'il la chèvre ?
                kb.requete("Mange('loups','chevre')");
                
                //Quels sont les animaux cruels ?
                kb.requete("Animal(x);Cruel(x)");
                
                //Quels sont les animaux cruels et que mangent-t'ils ?
                kb.requete("Animal(x);Cruel(x);Mange(x,y)");
                
                //Quels sont les animaux qui consomment la chèvre ?
                kb.requete("Animal(x);Consomme(x,'chevre')");
                
                //Que consomme la chevre ?
                kb.requete("Consomme('chevre',x)");
                
                */
            
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
                
                System.out.println("Bonne journée :)");


	}
}