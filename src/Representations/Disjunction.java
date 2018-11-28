package Representations;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Disjunction implements Constraint {
    LinkedHashMap<Variable, String> djt;

    public Disjunction(LinkedHashMap<Variable, String> djt) {
        this.djt = djt;
    }

    @Override
    public boolean isSatisfiedBy(LinkedHashMap<Variable, String> mycar) {
        boolean test1 = false;
        boolean test2 = false;
        for (Map.Entry<Variable, String> elt_dij : djt.entrySet()) {
            if (mycar.get(elt_dij.getKey()) == null) {
                test1=true;
            } else {
                if (mycar.get(elt_dij.getKey()).equals(elt_dij.getValue())) {
                    if (!test1 && !test2) {
                        test1 = true;
                    } else if (test1 && !test2) {
                        test2 = true;
                        test1 = false;
                    }
                }
            }
        }
        return(test1);
    }

    @Override
    public boolean filter(Map<Variable, String> x, Map<Variable, String> y) {
        return false;
    }
}
