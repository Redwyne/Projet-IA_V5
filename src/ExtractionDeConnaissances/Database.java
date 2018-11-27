package ExtractionDeConnaissances;

import javafx.util.Pair;
import Representations.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//que veut dire 'transcrire les instances'

public class Database extends BooleanDatabase{


    public Database(ArrayList<Transactions> liste_transactions, ArrayList<Variable> liste_variable) {
        super(liste_transactions, liste_variable);
    }









    public BooleanDatabase toBool(){
        HashMap<Variable,String> inutile=new HashMap<>();
        for(Variable var:liste_variable){
            inutile.put(var,"true");
        }
        Transactions tr=new Transactions(inutile);
        liste_transactions.add(tr);

        return new BooleanDatabase(liste_transactions,liste_variable);
    }
}
