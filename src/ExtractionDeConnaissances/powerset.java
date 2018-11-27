package ExtractionDeConnaissances;

import java.util.*;

public class powerset {
    public static <T> Set<Set<T>> powerset(Collection<T> list) {
        Set<Set<T>> ps = new HashSet<>();
        ps.add(new HashSet<>());   // add the empty set

        // for every item in the original list
        for (T item : list) {
            Set<Set<T>> newPs = new HashSet<>();

            for (Set<T> subset : ps) {
                // copy all of the current powerset's subsets
                newPs.add(subset);

                // plus the subsets appended with the current item
                Set<T> newSubset = new HashSet<T>(subset);
                newSubset.add(item);
                newPs.add(newSubset);
            }

            // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
            ps = newPs;
        }
        return ps;
    }

}
