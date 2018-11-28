package Representations;

import planning.State;

import java.util.*;

public interface Constraint {
    //public void getScope();

    boolean isSatisfiedBy(LinkedHashMap<Variable,String> a);
    boolean filter(Map<Variable,String> x,Map<Variable,String> y);
}
