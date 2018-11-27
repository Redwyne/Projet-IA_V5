package planning;
import Representations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class State {


    HashMap<Variable,String> etat;

    public State(HashMap<Variable, String> etat) {
        this.etat=etat;
    }

    public HashMap<Variable, String> affectation(ArrayList<Variable> tableau_var){
        for (Variable elt : tableau_var){

            Object[] l_dom =Arrays.copyOf(elt.getDomaine().toArray(), elt.getDomaine().size());
            etat.put(elt, (String) l_dom[0]);
        }
        return etat;
    }
    public HashMap<Variable, String> getEtat() {
        return etat;
    }

    public void AfficheEtat(){
        for (HashMap.Entry jsp : this.getEtat().entrySet()) {
            System.out.println(((Variable)jsp.getKey()).getNom());
            System.out.println(jsp.getValue());
        }
    }
}
