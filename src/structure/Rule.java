
package structure;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Rule {
    private ArrayList<Atom> hypRegle;
    private Atom conclusion;
    
    public Rule(String str){
        StringTokenizer st = new StringTokenizer(str,";");
        hypRegle = new ArrayList<Atom>();
        while(st.hasMoreTokens()){
                String s = st.nextToken();
                Atom a = new Atom(s);
                if(!st.hasMoreTokens()){
                   conclusion = a;
                }
                else{
                    hypRegle.add(a);
                }
        }
    }
    
    public ArrayList<Atom> getHypRegle() {
		return hypRegle;
	}

	public void setHypRegle(ArrayList<Atom> hypRegle) {
		this.hypRegle = hypRegle;
	}

	public Atom getConclusion() {
		return conclusion;
	}

	public void setConclusion(Atom conclusion) {
		this.conclusion = conclusion;
	}

	public String toString(){
        String str = "Hypothese de regle :" + hypRegle.toString();
        str += "\nConclusion : " + conclusion.toString();
        return str;
        
    }
}
