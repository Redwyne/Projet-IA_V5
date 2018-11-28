package ExtractionDeConnaissances;

import javafx.util.Pair;
import Representations.Variable;

import java.util.*;


public class Database extends BooleanDatabase{


    public Database(ArrayList<Transactions> liste_transactions, ArrayList<Variable> liste_variable) {
        super(liste_transactions, liste_variable);
    }


    public BooleanDatabase toBool(){
        Set<String> TF=new HashSet<>();
        TF.add("true");
        TF.add("false");
        ArrayList<Variable> var_remove=new ArrayList<>();
        ArrayList<Variable> var_added=new ArrayList<>();
        for (Variable var:liste_variable){
            if (var.getDomaine()!=TF){
                for (String x:var.getDomaine()){
                    String nwname=var.getNom().concat(x);
                    Variable boolvar=new Variable(nwname,TF);
                    var_remove.add(var);
                    var_added.add(boolvar);

                }
            }

        }
        liste_variable.removeAll(var_remove);
        liste_variable.addAll(var_added);
        return new BooleanDatabase(liste_transactions,liste_variable);
    }
}
