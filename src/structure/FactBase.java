package structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FactBase {
	private HashMap<String, ArrayList<ArrayList<Term>>> atoms;
	private ArrayList<Term> terms;
	private int nbFaits;

	public FactBase(String str){

		atoms = new HashMap<String, ArrayList<ArrayList<Term>>>();
		terms = new ArrayList<Term>();
		nbFaits = 0;

		StringTokenizer st = new StringTokenizer(str,";");
		while(st.hasMoreTokens()){
			String s = st.nextToken();
			Atom a = new Atom(s);
			ajoutFait(a);
			
		}
	}

	public ArrayList<Term> getTerm(){
		return terms;
	}

	public int getNbFaits(){
		return nbFaits;
	}

	public ArrayList<Term> getListConst(){
		ArrayList<Term> result = new ArrayList<Term>();
		for(int i = 0;i<terms.size();i++){
			if(terms.get(i).isConstant())
				result.add(terms.get(i));
		}
		return result;
	}

	public ArrayList<ArrayList<Term>> getAtomPred(String p){
		return atoms.get(p);
	}

	public void ajoutFait(Atom a){
		for(int i = 0; i<a.getArity();i++){
			boolean found = false;
			int j = 0;
			while(!found && j<terms.size() && terms.size() != 0){
				if(terms.get(j).equalsT(a.getArgI(i))){
					found = true;
				}
				j++;
			}
			if(!found){
				terms.add(a.getArgI(i));
			}					
		}

		if(!atoms.containsKey(a.getPredicate())){
			atoms.put(a.getPredicate(), new ArrayList());
		}
		atoms.get(a.getPredicate()).add(a.getArgs());
		nbFaits++;
		//ajouter à la base de fait (atoms)
	}
	
	public String toString(){
		String retour = "Liste des constantes : ";
		retour += this.getListConst().toString();
		retour += "\nNb de fait : ";
		retour += this.getNbFaits();
		retour +="\nListe des faits : ";

		for (Map.Entry<String, ArrayList<ArrayList<Term>>> entry : atoms.entrySet()) {
			String key = entry.getKey();
			ArrayList<ArrayList<Term>> value = entry.getValue();

			for(int i = 0; i<value.size();i++){
				String s = "\n" + key + "(";
				for(int j = 0; j<value.get(i).size();j++){
					if(j != 0)
						s +=",";
					s += value.get(i).get(j);
				}
				s += ")";
				retour += s;
			}
		}

		return retour;
	}
	
	public boolean contains(Atom a){
		String predicat = a.getPredicate();
		// recupere la liste de terme concernant le predicat de a
		ArrayList<ArrayList<Term>>  liste = atoms.get(predicat);

                if(liste == null){
                    return false;
                }
		// pour chaque liste de terme, on regarde si la liste est pareil que a, si oui alors true
		for(int i = 0; i<liste.size();i++){
                        boolean ok = true;
                        
                        ArrayList<Term> args = a.getArgs();
                        for(int j = 0; j<liste.get(i).size();j++){
                            if(!liste.get(i).get(j).equalsT(args.get(j))){
				ok = false;
                            }
                        }
                        if(ok){
                            return true;
                        }
			
		}
		return false;
		
	}
        
        public Object requete(ArrayList<Atom> Q){
            //Quand on a une requete avec 0 variables, on repond oui ou non
            //Quand on a une requete avec 1-n variables, il faut répondre les valeurs des variables
            
            // Premierement, on regarde si Q est une liste d'atome sans variables
            boolean reponseBooleen = true;
            for(int i = 0; i< Q.size();i++){
                ArrayList<Term> termes = Q.get(i).getArgs();
                for(int j = 0; j<termes.size();j++){
                    if(termes.get(j).isVariable()){
                        reponseBooleen = false;
                    }
                }
            }
            
            // Si Oui, on regarde si la requete est vrai ou faux
            if(reponseBooleen){
                for(int i = 0; i<Q.size();i++){
                    if(!this.contains(Q.get(i))){;
                        return false;
                    }
                }
                return true;
            }
            // Sinon, c'est un requete, il faut utiliser un homomorphisme (Actuel probleme, certaines requetes ont des constantes)
            else{
                Homomorphisms h = new Homomorphisms(Q, this);
                return h.homoQ;
            }
        }
}
