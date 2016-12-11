package csp;
import java.util.ArrayList;

/**
 * Permet de résoudre un problème de contrainte par Backtrack : 
 * 	Calcul d'une solution, 
 * 	Calcul de toutes les solutions
 *
 */
public class CSP {

		private Reseau reseau;						// le réseau à résoudre
		private ArrayList<Assignation> solutions; 	// les solutions du réseau
		private Assignation assignation;			// l'assignation courante
		int cptr;									// le compteur de noeuds explorés
		
		/**
		 * Crée un problème de résolution de contraintes pour un réseau donné
		 * 
		 * @param r le réseau de contraintes à résoudre
		 */
		public CSP(Reseau r) {
			reseau = r;
			solutions = new ArrayList<Assignation>();
			assignation = new Assignation();
		}
		
		/**
		 * Cherche une solution au réseau de contraintes
		 * 
		 * @return une assignation solution du réseau ou null si pas de solution
		 */
		public Assignation searchSolution() {
			cptr=0;
			assignation.clear();
			Assignation sol = backtrack();
			System.out.println("Solution trouvée : " + assignation.toString());
			System.out.println("Nombre de noeuds explorés :" + cptr);
			return sol;
		}

		/**
		 * Exécute l'algorithme de backtrack à la recherche d'une solution à partir de l'assignation courante
		 * 
		 * @return la prochaine solution ou null si pas de nouvelle solution
		 */
		private Assignation backtrack() {
			
			if(this.reseau.getVarNumber() == this.assignation.size()){
				cptr++;
				return assignation;
			}
			String x = chooseVar();
			ArrayList<Object> d = tri(reseau.getDom(x));
			for(int i = 0; i<d.size();i++){
				Assignation a = assignation.clone();
				assignation.put(x,d.get(i));
				cptr++;
				if(consistant(x)){
					if(backtrack() != null){
						return assignation;
					}
					else{
						assignation = a;
					}
				}
				else{
					assignation = a;
				}
			}
			return null;
		}

		/**
		 * Calcule toute les solutions au réseau de contraintes
		 * 
		 * @return la liste des assignations solution
		 * 
		 */
		public ArrayList<Assignation> searchAllSolutions(){
			cptr=0;
			solutions.clear();
			assignation = new Assignation();
			backtrackAll();
			//System.out.println("Toutes les solutions trouvées : " + solutions.toString());
			//System.out.println("Nombre de noeuds explorés :" + cptr);
			return solutions;
		}
		
		/**
		 * Exécute l'algorithme de backtrack à la recherche de toutes les solutions
		 * à partir de l'assignation courante en stockant solutions rencontrées.
		 * 
		 */
		private void backtrackAll() {
			if(this.reseau.getVarNumber() == this.assignation.size()){
				cptr++;
				solutions.add(assignation.clone());
			}
			else{
				String x = chooseVar();
				ArrayList<Object> d = reseau.getDom(x);
				for(int i = 0; i<d.size();i++){
					Assignation a = assignation.clone();
					assignation.put(x,d.get(i));
					cptr++;
					if(consistant(x)){
						backtrackAll();
					}
					assignation = a;
				}
			}
			
		}

	
		/**
		 * Retourne la prochaine variable à assigner
		 *  
		 * @return une variable non encore assignée
		 */
		private String chooseVar() {
			for(String var : reseau.getVars()){
				if(assignation.get(var) == null){
					return var;
				}
			}
			return null;
		}
		
		/**
		 * Fixe un ordre de prise en compte des valeurs d'un domaine
		 * 
		 * @param values une liste de valeurs
		 * @return une liste de valeurs
		 */
		private ArrayList<Object> tri(ArrayList<Object> values) {
			return values;
		}
		
		/**
		 * Teste si l'assignation courante est consistante, c'est à dire qu'elle
		 * ne viole aucune contrainte.
		 * 
		 * @param lastAssignedVar la variable que l'on vient d'assigner à cette étape.
		 * @return vrai ssi l'assignation courante ne viole aucune contrainte
		 */
		private boolean consistant(String lastAssignedVar) {
			ArrayList<Constraint> c = reseau.getConstraints(lastAssignedVar);
			for(int i = 0; i<c.size();i++){
				Constraint contrainte = c.get(i);
				if(contrainte.violation(assignation)){
					return false;
				}
			}
			return true;
		}
		
}
