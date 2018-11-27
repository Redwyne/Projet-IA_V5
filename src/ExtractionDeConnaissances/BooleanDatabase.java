package ExtractionDeConnaissances;

import java.util.ArrayList;

import Representations.*;
//ok qu est ce qu un itemset
//que font ces classses?
//0 ou 1==>"true" "false"
//qu est ce qu un “support minimal"

public class BooleanDatabase {
    ArrayList<Transactions> liste_transactions;
    ArrayList<Variable> liste_variable;

    public BooleanDatabase(ArrayList<Transactions> liste_transactions, ArrayList<Variable>liste_variable) {
        this.liste_transactions = liste_transactions;
        this.liste_variable = liste_variable;
    }

    public ArrayList<Transactions> getListe_transactions() {
        return liste_transactions;
    }

    public ArrayList<Variable> getListe_variable() {
        return liste_variable;
    }


}
