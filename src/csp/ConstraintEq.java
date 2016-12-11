package csp;
import java.io.BufferedReader;
import java.util.ArrayList;

public class ConstraintEq extends Constraint{
	public ConstraintEq(BufferedReader in) throws Exception{
		super(in);
	}

	@Override
	public boolean violation(Assignation a) {
		//On va pouvoir vérifier par transition
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
		
		Object obj = valeur.get(0);
		for(int i = 1; i<valeur.size();i++){
			if(!valeur.get(i).equals(obj)){
				return true;
			}
		}
		//return false si c'est pas une violation (tout est égal)
		return false;
	}
	
	public String toString(){
		return "\n\t Eq "+ super.toString();
	}
}
