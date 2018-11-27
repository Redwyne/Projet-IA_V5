
package ProgrammationParContraintes;

import Representations.*;

import java.util.*;

public class backtracking_nosol {
    public static LinkedHashMap<Variable,String> Test(ArrayList<Variable> tableauv, ArrayList<Constraint> tableauc) {

        LinkedHashMap<Variable,String> mycar = CarCreator(tableauv);
        boolean out = false;
        while (!out) {
            for (Constraint aTableauc : tableauc) {
                if (aTableauc.isSatisfiedBy(mycar)) {
                    out = true;
                } else {
                    out = false;
                    break;
                }
            }
            mycar= CarModifier(mycar,tableauv, out);
            if (mycar.size() < tableauv.size()) {
                out = false;
            }
            else {
                for (Constraint aTableauc : tableauc) {
                    if (aTableauc.isSatisfiedBy(mycar)) {
                        out = true;
                    } else {
                        out = false;
                        break;
                    }
                }
            }

        }
        String finalfkngstring="";
        for(Map.Entry<Variable, String> elt : mycar.entrySet()){
            finalfkngstring+=elt.getKey().getNom()+": "+elt.getValue()+" // ";
        }
        System.out.println(finalfkngstring);
        return mycar;
    }

    private static LinkedHashMap<Variable,String> CarCreator(ArrayList<Variable> tableauv) {
        LinkedHashMap<Variable, String> mycar = new LinkedHashMap<>();

        Object[] l_dom = Arrays.copyOf(tableauv.get(0).getDomaine().toArray(), tableauv.get(0).getDomaine().size());
        mycar.put(tableauv.get(0),(String) l_dom[0]);

        return mycar;
    }


    private static LinkedHashMap<Variable,String> CarModifier(LinkedHashMap<Variable, String> mycar, ArrayList<Variable> tableauv, boolean out) {

        if (out){
            //si mycar passe les tests, cherche la premiere variable non attribuée à mycar, l ajoute à mycar en affectant la premiere valeur puis retourne mycar
            for (Variable elt:tableauv){
                if (!mycar.containsKey(elt)){
                    System.out.println("on ajoute: "+elt.getNom());
                    Object[] l_dom = Arrays.copyOf(elt.getDomaine().toArray(),elt.getDomaine().size());
                    mycar.put(elt,(String) l_dom[0]);
                    return mycar;
                }
            }

        }else{
            //alerte on doit trouver un moyen de recuperer le dernier elemnt ajouté d un hashmap
            Map.Entry<Variable,String> last=null;
            Iterator it=mycar.entrySet().iterator();
            while(it.hasNext()){
                last=(Map.Entry)it.next();
            }
            Object[] l_dom = Arrays.copyOf(last.getKey().getDomaine().toArray(),last.getKey().getDomaine().size());
            for (int elt_suiv=0;elt_suiv<l_dom.length;elt_suiv++) {
                if (last.getValue().equals(l_dom[elt_suiv])) {
                    if ((elt_suiv + 1) >= l_dom.length) {
                        System.out.println("on retire: "+last.getKey().getNom());
                        mycar.remove(last.getKey());
                        break;

                    } else {
                        System.out.println("on change: "+last.getKey().getNom()+" ayant pour valeur: "+last.getValue());
                        mycar.put(last.getKey(), (String) l_dom[elt_suiv + 1]);
                        break;
                    }

                }
            }
            //System.out.println(mycar.entrySet().toArray()[mycar.size() -1]);
        }

        return mycar;
    }


}
