package Representations;

import java.util.Set;

public class Variable {

    public String nom;
    public Set<String> domaine;

    public Variable (String nom, Set<String> domaine){
        this.nom=nom;
        this.domaine=domaine;
    }

    public String getNom() {
        return nom;
    }

    public Set<String> getDomaine()
    {
        return domaine;
    }
}