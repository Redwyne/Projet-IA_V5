package Representations;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class IncompatibilityConstraint implements Constraint  {
    Set<Variable> IC;
    public IncompatibilityConstraint(Set<Variable> IC) {
        this.IC = IC;
    }

    @Override
    public boolean isSatisfiedBy(LinkedHashMap<Variable, String> mycar) {
        boolean out=true;
        loop:
        for (Variable x :IC) {
            for (Variable y :IC) {
                if (mycar.get(y)!=null){
                    if(!mycar.get(x).equals(mycar.get(y))){
                        out=false;
                        break loop;
                    }
                }
                else {
                    out=true;
                    break;
                }
            }
            return !out;

        }
        return !out;
    }

    @Override
    public boolean filter(Map<Variable, String> x, Map<Variable, String> y) {
        return false;
    }
}
