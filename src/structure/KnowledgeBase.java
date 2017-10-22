
package structure;

import csp.Assignation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class KnowledgeBase {
	FactBase bf;
	ArrayList<Rule> baseRegle;
	boolean sature;

	public KnowledgeBase(){
		bf = null;
		baseRegle = new ArrayList<Rule>();
		sature = false;
	}

	public KnowledgeBase(String chemin) throws IOException{
		sature = false;
		baseRegle = new ArrayList<Rule>();

		System.out.println("Chargement du fichier : " + new java.io.File(".").getCanonicalPath() + "/" + chemin);
		BufferedReader in = new BufferedReader(new FileReader(chemin));

		int nbFaits = Integer.parseInt(in.readLine());
		String baseDeFaitStr = "";
		for(int i = 0; i<nbFaits;i++){
			if(i == nbFaits -1){
				baseDeFaitStr += in.readLine();
			}
			else{
				baseDeFaitStr += in.readLine() + ";";
			}			
		}
		bf = new FactBase(baseDeFaitStr);
		// Le nombre de règles
		int nbRules = Integer.parseInt(in.readLine());
		for(int i = 0; i<nbRules; i++){
			String str = in.readLine();
			Rule r = new Rule(str);
			baseRegle.add(r);
		}
		// à partir de maintenant, ce n'est plus qu'une / des bases de faits
	}

	public FactBase getBf() {
		return bf;
	}

	public void setBf(FactBase bf) {
		this.bf = bf;
	}

	public ArrayList<Rule> getBaseRegle() {
		return baseRegle;
	}

	public void setBaseRegle(ArrayList<Rule> baseRegle) {
		this.baseRegle = baseRegle;
	}

	// C'est pas ca, faut faire un method dans baseFact pour ajouter un fait je pense
	//(on va en avoir besoin avec la saturation)
	public void ajoutFait(Atom a){
		bf.ajoutFait(a);
	}

	public void ajoutRegle(String str){
		baseRegle.add(new Rule(str));
	}

	public void ajoutRegle(Rule r){
		baseRegle.add(r);
	}

	public void saturerFaits(){
		boolean fin = false;
		while(!fin){
			ArrayList<Atom> nouveau = new ArrayList<Atom>();
			for(int i = 0; i < baseRegle.size();i++){
				Rule regle = baseRegle.get(i);
				if(reglePossible(regle)){
					Homomorphisms h = new Homomorphisms(regle.getHypRegle(), bf);
					// Pour chaque solution de l'homomorphisme
					for(int j = 0; j<h.homoQ.size();j++){
						Assignation assign = h.homoQ.get(j);

						//Constuction de la conclusion 
						Atom conclusion = new Atom(regle.getConclusion());
						for(int k = 0; k<conclusion.getArity();k++){
							Term element = conclusion.getArgI(k);
							if(element.isVariable()){
								Term res = (Term) assign.get(element.getLabel());
								conclusion.setArgI(k,res); // On a bien un Term :)   
							}                                       
						}
						// Si Conclusion n'est ni dans BF ni dans new on ajout conclusion dans new
						if(!bf.contains(conclusion) && !newContainConclusion(nouveau, conclusion)){
							nouveau.add(conclusion);
						}

					}
				}

			}
			if(nouveau.isEmpty()){
				fin = true;
			}
			else{
				for(int i = 0; i < nouveau.size();i++){
					bf.ajoutFait(nouveau.get(i));
				}
			}
		}
		sature = true;
	}

	private boolean newContainConclusion(ArrayList<Atom> nouveau, Atom a){
		for(int i = 0; i<nouveau.size();i++){
			if(nouveau.get(i).equalsA(a)){
				return true;
			}
		}
		return false;
	}

	// Regle possible va regarder si chaque atome de l'hypothese est present en BF
	private boolean reglePossible(Rule r){
		ArrayList<Atom> hyp = r.getHypRegle();
		for(int i = 0 ; i<hyp.size();i++){
			if(bf.getAtomPred(hyp.get(i).getPredicate()) == null){
				return false;
			}
		}
		return true;
	}

	public String toString(){
		String str = "Base de fait :\n" + bf.toString();
		str += "\n\nListe des règles :";
		for(int i = 0; i<baseRegle.size();i++){
			str += "\nRègle n°" + i + " : \n";
			str += baseRegle.get(i).toString();
		}
		return str;   
	}

	public String requete(String str){
		if(!sature){
			saturerFaits();
			System.out.println(bf);
		}            

		System.out.println("----------------- Requete : " + str + "------------------------");

		ArrayList<Atom> Q = Homomorphisms.changeStrToListAtom(str);

		Object obj = bf.requete(Q);

		System.out.println(obj);

		//System.out.println(h);
		// maintenant il faut s'occuper de la requete.
		return null;
	}
}
