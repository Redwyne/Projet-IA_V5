package Representations;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AllEqualConstraint implements Constraint {
    public Set<Variable> AEC;
    public AllEqualConstraint(Set<Variable> AEC) {
        this.AEC=AEC;
    }



    @Override
    public boolean isSatisfiedBy(LinkedHashMap<Variable, String> mycar){
        boolean out=true;
        loop:
        for (Variable x :AEC) {
            for (Variable y :AEC) {
                if (mycar.get(y) == null || mycar.get(x)==null) {
                    out=true;
                } else if (!mycar.get(x).equals(mycar.get(y))) {
                    out = false;
                    break loop;
                }
            }
            return true;
            }
        return out;
    }

    @Override
    public boolean filter(Map<Variable, String> x, Map<Variable, String> y) {
        return false;
    }
}