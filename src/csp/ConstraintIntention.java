package csp;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import javax.script.*;

public class ConstraintIntention extends Constraint{
	String nb;
	
	public ConstraintIntention(BufferedReader in) throws Exception{
		super(in);
		nb = in.readLine();
	}
	
	@Override
	public boolean violation(Assignation a) {
		HashMap<String,Object> valeur = new HashMap<String,Object>();
		ArrayList<String> variables = a.getVars();
		for(int i = 0; i<this.varList.size();i++){
			for(int j = 0; j<variables.size();j++){
				if(variables.get(j).equals(varList.get(i))){
					valeur.put(variables.get(j),a.get(variables.get(j)));
				}
			}
		}
		if(valeur.size() != varList.size()){
			return false;
		}
		
		//System.out.println(nb);
		//System.out.println(valeur.toString());
		//Valeur des variables
		//System.out.println(valeur.size());
		Set cle = valeur.keySet();
		Iterator it = cle.iterator();
		String copynb = new String(nb);
		while(it.hasNext()){
			Object c = it.next();
			copynb = copynb.replaceAll((String)c, (String)valeur.get(c));
		}
		copynb = copynb.replaceAll("=", "==");
		//System.out.println(copynb);
		try{
			//Evalutation du truc machin bidule chouette
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");        
			Object result = engine.eval(copynb);
			//System.out.println(result);
			boolean resultat = (boolean)result;
			if(!resultat){ // Si l'évaluation renvoi false (0) il y a violation sinon c'est OK 
				return true;
			}	
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
		

		return false;
	}
	
	public String toString(){
		return "\n\t Int "+ super.toString();
	}

}
