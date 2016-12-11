package csp;
import java.io.BufferedReader;
import java.util.ArrayList;

public class ConstraintDif extends Constraint{
	public ConstraintDif(BufferedReader in) throws Exception{
		super(in);
	}

	@Override
	public boolean violation(Assignation a) {
		// System.out.println(a);
		// System.out.println(this.getVars());
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
		
		for(int i = 0; i<valeur.size();i++){
			for(int j = 0; j<valeur.size();j++){
				if(valeur.get(i).equals(valeur.get(j)) && i != j){
					return true;
				}
			}
		}
		return false;
		
	}
	
	public String toString(){
		return "\n\t Dif "+ super.toString();
	}
}
