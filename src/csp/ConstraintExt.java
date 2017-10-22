package csp;
import java.io.BufferedReader;
import java.util.ArrayList;

import structure.Term;

/**
 * Pour manipuler des contraintes en extension
 *
 */
public class ConstraintExt extends Constraint{

	private ArrayList<ArrayList<Object>> tuples;	// ensemble des tuples de la contrainte

	/**
	 * Construit une contrainte d'extension vide à partir
	 * d'une liste de variables
	 * 
	 * @param var la liste de variables
	 */
	public ConstraintExt(ArrayList<String> var) {
		super(var);
		tuples = new ArrayList<ArrayList<Object>>();
	}

	/**
	 * Construit une contrainte d'extension vide à partir
	 * d'une liste de variables et dun nom
	 * 
	 * @param var la liste de variables
	 * @param name son nom
	 */
	public ConstraintExt(ArrayList<String> var, String name) {
		super(var,name);
		tuples = new ArrayList<ArrayList<Object>>();
	}

	/**
	 * Construit une contrainte en extension à partir d'une représentation
	 * textuelle de la contrainte. La liste de variables est donnée sous la forme : Var1;Var2;...
	 * Puis le nombre de tuples est indiqué et enfin chaque tupe est donné sous la forme d'une
	 * suite de valeurs "String" : Val1;Val2;...
	 * Aucune vérification n'est prévue si la syntaxe n'est pas respectée !!
	 * 
	 * @param in le buffer de lecture de la représentation textuelle de la contrainte
	 * @throws Exception en cas d'erreur de format
	 */
	public ConstraintExt(BufferedReader in) throws Exception{
		super(in);
		tuples = new ArrayList<ArrayList<Object>>();
		int nbTuples = Integer.parseInt(in.readLine());		// nombre de tuples de valeurs
		for(int j=1;j<=nbTuples;j++) {
			ArrayList<Object> tuple = new ArrayList<Object>();
			for (String v : in.readLine().split(";")) tuple.add(v);	// Val1;Val2;...;Val(arity)
			if(tuple.size() != varList.size()) System.err.println("Le tuple " + tuple + " n'a pas l'arité " + varList.size() + " de la contrainte " + name);
			else if(!tuples.add(tuple)) System.err.println("Le tuple " + tuple + " est déjà présent dans la contrainte "+ name);
		}
	}

	/*
	 * var : liste des variables  ? 
	 * 
	 *
	 */
	public ConstraintExt(ArrayList<String> var, ArrayList<ArrayList<Term>> val) throws Exception{
		super(var);
		tuples = new ArrayList<ArrayList<Object>>();                
		for(int i=0; i<val.size();i++){
			ArrayList<Object> obj = new ArrayList<Object>();
			for(int j=0; j<val.get(i).size();j++){
				obj.add(val.get(i).get(j));
			}
			tuples.add(obj);
		}
	}

	/**
	 * Ajoute un tuple de valeur à la contrainte
	 * 
	 * @param valTuple le tuple à ajouter
	 */
	public void addTuple(ArrayList<Object> valTuple) {
		if(valTuple.size() != varList.size()) System.err.println("Le tuple " + valTuple + " n'a pas l'arité " + varList.size() + " de la contrainte " + name);
		else if(!tuples.add(valTuple)) System.err.println("Le tuple " + valTuple + " est déjà présent dans la contrainte "+ name);
	}


	/* (non-Javadoc)
	 * A Implanter !
	 * @see Constraint#violation(Assignation)
	 */
	public boolean violation(Assignation a) {
		//System.out.println(a);
		//System.out.println(this.getVars());
		ArrayList<Object> valeur = new ArrayList<Object>();
		ArrayList<String> variables = a.getVars();
		for(int i = 0; i<this.varList.size();i++){
			for(int j = 0; j<variables.size();j++){
				if(variables.get(j).equals(varList.get(i))){
					valeur.add(a.get(variables.get(j)));
				}
			}
		}
		if(valeur.size() != varList.size()){
			return false;
		}

		//System.out.println(this.tuples);
		//System.out.println(a);
		// Boucle sur les contraintes
		for(int i = 0; i<this.tuples.size();i++){
			boolean ok = false;
			ArrayList<Object> obj = this.tuples.get(i);
			//boucle sur les assignations
			for(int j = 0; j<obj.size();j++){
				//System.out.println(obj.get(j).getClass());
				//System.out.println(valeur.get(j).getClass());
				//System.out.println(obj.get(j) +" vs " + valeur.get(j));
				Object valTuple = obj.get(j);
				Object valVerif = valeur.get(j);
				if((valTuple instanceof Term) && (valVerif instanceof Term)){
					if(((Term)valTuple).equals((Term)valVerif)){
						if(ok == true && j == obj.size()-1){
							return false;
						}
						else if(obj.size() == 1){
							return false;
						}
						ok = true;
					}
				}
				else{
					if(valTuple.equals(valVerif)){
						if(ok == true && j == obj.size()-1){
							return false;
						}
						else if(obj.size() == 1){
							return false;
						}
						ok = true;
					}
				}
			}
			ok = false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see Constraint#toString()
	 */
	public String toString() {
		return "\n\t Ext "+ super.toString() + " : " + tuples; 
	}


}
