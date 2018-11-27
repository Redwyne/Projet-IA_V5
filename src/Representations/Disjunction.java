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
        boolean oui = false;
        boolean ouioui = false;
        for (Map.Entry<Variable, String> elt_dij : djt.entrySet()) {
            if (mycar.get(elt_dij.getKey()) == null) {
                oui=true;
            } else {
                if (mycar.get(elt_dij.getKey()).equals(elt_dij.getValue())) {
                    if (!oui && !ouioui) {
                        oui = true;
                    } else if (oui && !ouioui) {
                        ouioui = true;
                        oui = false;
                    }
                }
            }
        }
        return(oui);
    }

    @Override
    public boolean filter(Map<Variable, String> x, Map<Variable, String> y) {
        return false;
    }
}
