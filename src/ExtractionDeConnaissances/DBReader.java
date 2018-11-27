
package ExtractionDeConnaissances;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import Representations.Variable;

public class DBReader {

    protected Set<Variable> variables;

    public DBReader(Set<Variable> variables) {
        this.variables = variables;
    }
    public Database importDB (String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader (new FileReader (filename))) {
            Database res = this.readDB(reader);
            reader.close();
            return res;
        }
    }

    public Database readDB(BufferedReader in) throws IOException {
        // Reading variables
        ArrayList<Variable> orderedVariables = new ArrayList<>();
        String variableLine = in.readLine();
        for (String variableName: variableLine.split(";")) {
            boolean found = false;
            for (Variable variable: this.variables) {
                if (variable.getNom().equals(variableName)) {
                    orderedVariables.add(variable);
                    found = true;
                    break;
                }
            }
            if ( ! found ) {
                throw new IOException("Unknown variable name: " + variableName);
            }
        }
        // Reading instances
        List<HashMap<Variable, String>> instances = new ArrayList<>();
        String line;
        int lineNb = 1;
        while ( (line = in.readLine()) != null ) {
            String [] parts = line.split(";");
            if (parts.length != orderedVariables.size()) {
                throw new IOException("Wrong number of fields on line " + lineNb);
            }
            HashMap<Variable, String> instance = new HashMap<> ();
            for (int i = 0; i < parts.length; i++) {
                instance.put(orderedVariables.get(i), parts[i]);
            }
            instances.add(instance);
            lineNb++;
        }
        ArrayList<Transactions>instances_al=new ArrayList<>();
        for (HashMap<Variable,String> oui:instances){
            Transactions tr=new Transactions(oui);
            instances_al.add(tr);
        }
        return new Database(instances_al,orderedVariables);
    }

}
