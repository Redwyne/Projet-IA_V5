package ExtractionDeConnaissances;

import java.util.*;

import Representations.Variable;
//prend les itemsets de longueur1, envoie leurs freq, puis regarde les itemsets de longueur 2.
public class FrequentItemsetMinerBoolean {
    BooleanDatabase bd;

    public FrequentItemsetMinerBoolean(BooleanDatabase bd) {
        this.bd = bd;
    }

    public BooleanDatabase getBd() {
        return bd;
    }

    public HashMap<Set<Variable>,Integer> FrequentItemsets(int minfr){
        Set powset=new HashSet();
        HashMap<Set<Variable>,Integer> res=new HashMap();
        for (Variable var:bd.getListe_variable()) {
            Set<Variable> set_temp=new HashSet();
            int cpt=0;
            for (Transactions transac : bd.getListe_transactions()) {
                for (Map.Entry<Variable, String> entry : transac.getTransactions().entrySet()){
                    if (entry.getValue()=="1" && var.getNom()==entry.getKey().getNom()){
                        cpt+=1;
                    }
                }
            }
            if (cpt>minfr) {
                set_temp.add(var);
                res.put(set_temp, cpt);
                powset.add(var.getNom());

            }
        }
        Set<Set<Integer>> set_final=powerset.powerset(powset);
        System.out.println(set_final);
        int k=2;
        while((set_final=createset(set_final,k)).size()>0){
            Set<Set<Integer>> pwst=new HashSet();
            int cpt2=0;
            for (Set<Integer> set_encours:set_final){
                for (Transactions transac:bd.getListe_transactions()){
                    for (Map.Entry<Variable, String> entry : transac.getTransactions().entrySet()){
                        System.out.println(entry.getValue());
                        if (entry.getValue()=="1" ){ //et si on est sur la variable AB, qui n existe pas.. nik
                            pwst.add(set_encours);
                            cpt2+=1;
                        }
                    }
                }
            }
            k+=1;
        }



        return res;
    }
    //createset permet de retirer tous les sets de set_final n'ayant pas la taille minimale k
    public Set<Set<Integer>> createset(Set<Set<Integer>> set_final,int k){
        Set<Set<Integer>> set_jsp=new HashSet<>();
        Iterator it=set_final.iterator();
        while(it.hasNext()){
            Set<Integer> obj=(Set) it.next();
            if (obj.size()==k){
                System.out.println(obj);
                set_jsp.add(obj);
            }
        }
        return set_jsp;
    }
}
