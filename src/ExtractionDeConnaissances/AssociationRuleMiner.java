package ExtractionDeConnaissances;

import java.util.*;

import Representations.*;
import planning.State;

public class AssociationRuleMiner {
    Map<Set<Variable>, Integer> hm_itemset;

    public ArrayList<Rule> Association(HashMap<HashSet<Variable>, Integer> res) {
        ArrayList<Rule> al_rule = new ArrayList<>();
        for (Map.Entry<HashSet<Variable>, Integer> set_v : res.entrySet()){
            Iterator it = set_v.getKey().iterator();
            while (it.hasNext()) {
                Variable elt = (Variable) it.next();
                HashSet<Variable> secondset;
                secondset=(HashSet<Variable>) set_v.getKey().clone();
                secondset.remove(elt);
                Rule rl=new Rule(new State(to_hm(elt)),new State(to_hm(secondset)));
                al_rule.add(rl);

            }
        }
        return al_rule;
    }
    public LinkedHashMap<Variable,String> to_hm(Variable a){
        LinkedHashMap<Variable,String> hm=new LinkedHashMap();
        hm.put(a,"true");

        return hm;
    }
    public LinkedHashMap<Variable,String> to_hm(HashSet<Variable> a){
        LinkedHashMap<Variable,String> hm=new LinkedHashMap();
        for (Variable elt: a){
            hm.put(elt,"true");
        }

        return hm;
    }
}
