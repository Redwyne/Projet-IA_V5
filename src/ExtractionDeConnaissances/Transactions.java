package ExtractionDeConnaissances;

import Representations.Variable;

import java.util.HashMap;

public class Transactions {
    HashMap<Variable,String> transactions;

    public Transactions(HashMap<Variable, String> transactions) {
        this.transactions = transactions;
    }

    public HashMap<Variable, String> getTransactions() {
        return transactions;
    }

    public void setTransactions(HashMap<Variable, String> transactions) {
        this.transactions = transactions;
    }
}
