package planning;
import Representations.*;

import java.util.*;

public class State {


    LinkedHashMap<Variable,String> etat;

    public State(LinkedHashMap<Variable, String> etat) {
        this.etat=etat;
    }

    public LinkedHashMap<Variable, String> affectation(ArrayList<Variable> tableau_var){
        for (Variable elt : tableau_var){

            Object[] l_dom =Arrays.copyOf(elt.getDomaine().toArray(), elt.getDomaine().size());
            etat.put(elt, (String) l_dom[0]);
        }
        return etat;
    }
    public LinkedHashMap<Variable, String> getEtat() {
        return etat;
    }

    public void AfficheEtat(){
        for (Map.Entry jsp : this.getEtat().entrySet()) {
            System.out.println(((Variable)jsp.getKey()).getNom());
            System.out.println(jsp.getValue());
        }
    }
}
