package ProgrammationParContraintes;

import Representations.*;
import planning.State;

import java.util.*;

public class backtracking {
    private static ArrayList<Variable> tableauv;
    private static ArrayList<Constraint> tableauc;
    private boolean out=false;

    public backtracking(ArrayList<Variable> tableauv, ArrayList<Constraint> tableauc) {
        backtracking.tableauv = tableauv;
        backtracking.tableauc = tableauc;
    }
    private LinkedHashMap<Variable,String> Test(LinkedHashMap<Variable,String> mycar) {
        while (!out) {
            System.out.println(mycar);
            String fks="";
            for(Map.Entry<Variable, String> elt_sol : mycar.entrySet()){
                fks+=elt_sol.getKey().getNom()+": "+elt_sol.getValue()+" // ";
            }
            System.out.println(fks);
            for (Constraint aTableauc : tableauc) {
                if (aTableauc.isSatisfiedBy(mycar)) {
                    out = true;
                } else {
                    out = false;
                    break;
                }
            }
            mycar = CarModifier(mycar, out);
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

        return mycar;
    }

    private LinkedHashMap<Variable, String> CarCreator() {
        LinkedHashMap<Variable, String> mycar = new LinkedHashMap<>();
        Object[] l_dom = Arrays.copyOf(tableauv.get(0).getDomaine().toArray(), tableauv.get(0).getDomaine().size());
        mycar.put(tableauv.get(0), (String) l_dom[0]);

        return mycar;
    }


    private LinkedHashMap<Variable, String> CarModifier(LinkedHashMap<Variable, String> mycar, boolean out) {
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
            System.out.println("mycar contient toutes les variables possibles");

        }else{
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
                        CarModifier(mycar,false);
                        break;

                    } else {
                        System.out.println("on change: "+last.getKey().getNom()+" ayant pour valeur: "+last.getValue());
                        mycar.put(last.getKey(), (String) l_dom[elt_suiv + 1]);
                        break;
                    }

                }
            }
        }

        return mycar;
    }

    public ArrayList solution() {
        ArrayList<HashMap<Variable,String>> res=new ArrayList<>();
        //voiture n1
        LinkedHashMap<Variable, String> voiture_ex = Test(CarCreator());

        //voiture n2 (clonée de la premiere)
        LinkedHashMap<Variable,String> voiture_ex2=(LinkedHashMap<Variable,String>)voiture_ex.clone();
        voiture_ex2=CarModifier(voiture_ex2,out=false);
        voiture_ex2=Test(voiture_ex2);

        //voiture n3 (clonée de la 2nde)
        LinkedHashMap<Variable,String> voiture_ex3=(LinkedHashMap<Variable,String>)voiture_ex2.clone();
        voiture_ex3=CarModifier(voiture_ex3,out=false);
        voiture_ex3=Test(voiture_ex3);

        res.add(voiture_ex);
        res.add(voiture_ex2);
        res.add(voiture_ex3);
        for (HashMap<Variable,String> sol:res){
            String affichesol="";
            for(Map.Entry<Variable, String> elt_sol : sol.entrySet()){
                affichesol+=elt_sol.getKey().getNom()+": "+elt_sol.getValue()+" // ";
            }
            System.out.println(affichesol);
        }

        return res;
    }
}