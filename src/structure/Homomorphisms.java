package structure;

import java.util.ArrayList;
import java.util.StringTokenizer;

import csp.*;

public class Homomorphisms {
	ArrayList<Atom> Q;
	FactBase bf;
	ArrayList<Assignation> homoQ;
	
	
	public Homomorphisms(String q, FactBase b){
		Q = changeStrToListAtom(q);
		bf = b;
        homoQ = BacktrackAll();
	}
	
	public Homomorphisms(ArrayList<Atom> q, FactBase b){
		Q = q;
		bf = b;
		homoQ = BacktrackAll();
		//il faut faire les homomorphismes de q dans bf
	}
	
	private ArrayList<Atom> changeStrToListAtom(String str){
		StringTokenizer st = new StringTokenizer(str,";");
		ArrayList<Atom> at = new ArrayList<Atom>();
		while(st.hasMoreTokens()){
			String s = st.nextToken();
			Atom a = new Atom(s);
			at.add(a);
		}
		return at;
	}
	
	private Reseau changeToReseau(){
		Reseau reseau = new Reseau();
		ArrayList<Term> constantes = bf.getListConst();
		for(int i=0; i<Q.size();i++){
			for(int j=0; j<Q.get(i).getArity();j++){
				Term terme = Q.get(i).getArgI(j);
				if(!terme.isConstant()){
                                    if(reseau.getDom(terme.getLabel()) == null){
                                        reseau.addVariable(terme.getLabel());
					for(int k = 0; k<constantes.size();k++){
						reseau.addValue(terme.getLabel(), constantes.get(k));
					}
                                    }
				}
			}
		}
		for(int i = 0; i<Q.size();i++){
			ArrayList<Term> vars = Q.get(i).getArgs();
			ArrayList<String> variables = new ArrayList<String>();
			//Boucle qui crée la liste des variables, servant à la contrainte
			for(int j=0;j<vars.size();j++){
				//Je pense qu'il va falloir gérer ici les problèmes de variables qui ont le meme nom NYI
				variables.add(vars.get(j).getLabel());
			}
			Constraint c = null;
			try {
				// le deuxieme argument, va recupérer dans le hmap les listes des constantes
				c = new ConstraintExt(variables, bf.getAtomPred(Q.get(i).getPredicate()));
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			reseau.addConstraint(c);

		}
		//System.out.println(reseau.toString());        
		//reste plus qu'à ajouter les contraintes
		return reseau;
	}
        
        public ArrayList<Assignation> BacktrackAll(){
            Reseau reseau = this.changeToReseau();
            CSP resultat = new CSP(reseau);
            return resultat.searchAllSolutions();
        }
        
        public String toString(){
            String str ="";
            str += "Q = " + Q.toString();
            str += "\n";
            str += "Base de fait :\n" + bf.toString();
            str += "\n";
            if(homoQ.size()>0){
                str += "Solutions trouvées : " + homoQ.toString();
            }
            else{
                str += "Aucune solution trouvé";
            }
            return str;
        }
	
	
}
