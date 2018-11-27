package Representations;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConstraintDisjunction implements Constraint{


    @Override
    public boolean isSatisfiedBy(LinkedHashMap<Variable, String> a) {
        return false;
    }

    @Override
    public boolean filter(Map<Variable, String> x, Map<Variable, String> y) {
        return false;
    }
}
