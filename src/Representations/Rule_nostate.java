package Representations;


import planning.State;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Rule_nostate implements Constraint {

    HashMap<Variable,String> premisse;
    HashMap<Variable,String> conclusion;

    public Rule_nostate(HashMap<Variable,String> premisse, HashMap<Variable,String> conclusion) {
        this.premisse = premisse;
        this.conclusion = conclusion;
    }

    @Override
    public boolean isSatisfiedBy(LinkedHashMap<Variable, String> mycar) {
        boolean p = true;
        boolean c = true;
        for (Map.Entry<Variable, String> entry : premisse.entrySet()) {
            Variable x = entry.getKey();
            if (mycar.get(x)==(null)){
                p=true;
            }
            else if (mycar.get(x).equals(entry.getValue())) {
                p = true;
            } else {
                p = false;
                break;
            }

        }

        if (p) {
            for (Map.Entry<Variable, String> entry : conclusion.entrySet()) {
                Variable y = entry.getKey();
                if (mycar.get(y)==(null)){
                    c=true;
                }
                else if (! mycar.get(y).equals(entry.getValue())) {
                    c=false;
                }else{
                    c=true;
                    break;
                }

            }
        }
        return (!p || c) ;
    }

    @Override
    public boolean filter(Map<Variable, String> x, Map<Variable, String> y) {
        return false;
    }
}
