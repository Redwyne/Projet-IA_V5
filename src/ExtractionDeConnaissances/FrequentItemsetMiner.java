package ExtractionDeConnaissances;

import java.util.*;

import Representations.*;

//prend les itemsets de longueur1, envoie leurs freq, puis regarde les itemsets de longueur 2.
public class FrequentItemsetMiner {
    private Database bd;

    public FrequentItemsetMiner(Database bd) {
        this.bd = bd;
    }




    public LinkedHashMap<Set<Map.Entry<Variable,String>>,Integer> FrequentItemsets(int minfr) {
        LinkedHashMap<Map.Entry<Variable, String>, Integer> restemp=new LinkedHashMap<>();
        LinkedHashMap<Set<Map.Entry<Variable, String>>, Integer> res = new LinkedHashMap<>();
        Set<Map.Entry<Variable, String>> powset = new HashSet<>();
        for (Transactions transac : bd.getListe_transactions()) {
            for (Map.Entry<Variable, String> entry : transac.getTransactions().entrySet()) {

                if (!restemp.containsKey(entry)) {
                    restemp.put(entry, 1);
                } else {
                    restemp.put(entry, restemp.get(entry) + 1);
                }
            }
        }

        for (Map.Entry<Map.Entry<Variable, String>, Integer> elt : restemp.entrySet()) {
            if (elt.getValue() >= minfr) {
                Set<Map.Entry<Variable,String>> elt_s=new HashSet<>();
                elt_s.add(elt.getKey());
                res.put(elt_s,elt.getValue());
                powset.add(elt.getKey());
            }
        }
        Set<Set<Map.Entry<Variable, String>>> set_f = powerset.powerset(powset);
        int k = 2;
        Set<Set<Map.Entry<Variable, String>>> set_k ;
        while ((set_k = createset(set_f, k)).size() > 0) {
            HashMap<Set<Map.Entry<Variable, String>>, Integer> restemp2 = new HashMap<>();
            //elt_pws est un set de (Variable+valeur), ex:A=1,B=1,C=0
            for (Set<Map.Entry<Variable, String>> elt_pws : set_k) {
                for (Transactions transac : bd.getListe_transactions()) {
                    int cpt=0;
                    //elt est une des variables+sa valeur de mon set de var, ex: A=0
                    for (Map.Entry<Variable, String> elt : elt_pws) {
                        //entry est ma transactions en cours, donc un enchainement d'affectations de variables, ex: A=1,B=1,C=0
                        for (Map.Entry<Variable, String> entry : transac.getTransactions().entrySet()) {
                            if (elt.equals(entry)){
                                cpt+=1;
                            }
                            if (cpt==k){
                                if (!restemp2.containsKey(elt_pws)){
                                    restemp2.put(elt_pws,1);
                                }
                                else{
                                    restemp2.put(elt_pws,restemp2.get(elt_pws)+1);
                                }
                                break;
                            }

                        }
                    }
                }
                for (Map.Entry<Set<Map.Entry<Variable, String>>, Integer> elt : restemp2.entrySet()) {
                    if (elt.getValue() >= minfr) {
                        res.put(elt.getKey(),elt.getValue());
                        powset.addAll(elt_pws);
                    }
                }
            }
            k += 1;
        }
        for (Map.Entry<Set<Map.Entry<Variable,String>>,Integer> elt:res.entrySet()){
            String tm="";
            for (Map.Entry<Variable,String> elt2:elt.getKey()){
                tm+=elt2.getKey().getNom();
            }
            System.out.println(tm+"="+elt.getValue());
        }
        return res;
    }

    //createset permet de retirer tous les sets de set_final n'ayant pas la taille minimale k
    private Set<Set<Map.Entry<Variable,String>>> createset(Set<Set<Map.Entry<Variable,String>>> set_final,int k){
        Set<Set<Map.Entry<Variable,String>>> set_jsp=new HashSet<>();
        Iterator it=set_final.iterator();
        while(it.hasNext()){
            Set<Map.Entry<Variable,String>> obj=(Set<Map.Entry<Variable,String>>) it.next();
            if ((obj.size()==k) && (obj.size()!=0)){
                set_jsp.add(obj);
            }
        }
        return set_jsp;

    }
}
