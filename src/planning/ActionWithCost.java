package planning;

import Representations.Rule;
import Representations.Variable;

import java.util.ArrayList;
import java.util.Map;

public class ActionWithCost extends Action {
    static int cout;
    public ActionWithCost(ArrayList<Rule> rules,int cout) {
        super(rules);
        this.cout=cout;
    }
    public static boolean satisfies(State etat, State etat_partiel) {
        for (Map.Entry<Variable,String> elt : etat_partiel.getEtat().entrySet()) {
            //si l'élement en cours de l'état partiel n'est pas dans l'état, ou si la valeur de l'élément en cours est différent entre l'état et le partiel alors faux
            if (!(etat.getEtat().containsKey(elt.getKey())) || !etat.getEtat().get(elt.getKey()).equals(elt.getValue())) {
                System.out.println(elt.getKey().getNom()+": "+etat.getEtat().get(elt.getKey())+"=/="+elt.getValue());
                return false;
            }
        }

        return true;
    }

    public static boolean is_applicable(ActionWithCost action, State etat){
        for (Rule rl: action.rules){
            if (satisfies(etat,rl.getPremisse())){
                return true;
            }
        }
        return false;
    }



    public State apply(State etat) {
        for (Rule rl: this.rules){
            if(satisfies(etat,rl.getPremisse())){
                for (Map.Entry<Variable,String> me : rl.getConclusion().getEtat().entrySet()) {
                    etat.getEtat().put(me.getKey(), me.getValue());
                }
            }
        }
        return etat;
    }
}

