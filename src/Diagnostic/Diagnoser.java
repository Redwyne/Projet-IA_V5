package Diagnostic;

import Representations.Constraint;
import Representations.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Diagnoser {
    HashMap<Variable,String> ens_choix;
    ArrayList<Constraint> ens_constr;

    public Diagnoser(HashMap<Variable,String> ens_choix, ArrayList<Constraint> ens_constr) {
        this.ens_choix = ens_choix;
        this.ens_constr = ens_constr;
    }
    public HashMap<Variable,String> delvarval(Variable var){
        ens_choix.remove(var);
        return null;
    }

    public HashMap<Variable,String> addvarval(Variable var,String val){
        ens_choix.put(var,val);
        return null;
    }


    public boolean est_expl(HashMap<Variable,String> ens_candidat,HashMap<Variable,String> choixinterdit){
        LinkedHashMap<Variable,String> ens_choix_temp=new LinkedHashMap<>();
        ens_choix_temp.putAll(ens_candidat);
        ens_choix_temp.putAll(choixinterdit);
        for (Constraint cons:ens_constr){
            if(!cons.isSatisfiedBy(ens_choix_temp)) {
                return false;
            }
        }
        return true;
    }
    public HashMap<Variable, String> expli(HashMap<Variable,String> ens_choix, HashMap<Variable, String> choixinterdit){
        HashMap<Variable,String> CNE=new HashMap<>();
        CNE.putAll(ens_choix);
        HashMap<Variable,String> res=new HashMap<>();
        res.putAll(ens_choix);
        for (Map.Entry xval: CNE.entrySet()){
            HashMap<Variable,String> resprime=new HashMap<>();
            resprime.putAll(res);
            resprime.remove(xval);
            if (est_expl(resprime,choixinterdit)){
                res=resprime;
            }


        }

        return res;
    }
}

/*
Relacher un choix = renoncer à l'affectation d une Variable
CSP=ensemble (variable domaine contraintes)
Etant donné un CSP N,
un ensemble de choix C={VARi=VALi}
un choix interdit VAR=VAL
sortie: L'ensemble de toutes les explications inclusions minimales de VAR=/=VAL sachant N et C
Definition: une explication de VAR=/=VAL sachant N et C est un sous-ensemble E des choix déjà faits
tel qu'il n existe pas de solution du réseau de N qui soit cohérent avec E et VAR=VAL
Definition: une explication E est dite inclusion minimale si pour tout C' inclu dans C,
C' n'est pas une explication de VAR=VAL sachant N et C
Proposition: pour retrouver le choix VAR=VAL, il faut et il suffit de relâcher au moins un
choix par explication minimale de VAR=/=VAL (desaffecter la Variable)

Algorithme: paramètres: N C choixinterdit
    Sort une solution
puis sort une enumération de solutions
Il existe toujours au moins une explcation E={C}
On va donc minimiser cette explication:
While true:
    choisir VAR1=VAL1 dans F
    E'=E\{VARi=VALi}
    Si C' est toujours une explication dans F alors E<-E'
        F'=F\{VARi=VALi}

Algo: N E {VARi=VALi}, VAR=VAL  ->return bool
E est elle une explication de VAR=/=VAL selon N
Soit N'=N avec le domaine(VAri)={Vali} pour tout VARi,VALi appartenant a E et domaine (VAR)={VAL}
   return not(N' a t'il une solution?)
 */