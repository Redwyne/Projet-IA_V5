
package examples;


import java.util.*;
import java.util.concurrent.*;

import planning.*;
import Representations.*;
import planning.*;

public class AssemblyLine {
    LinkedHashMap<Variable,String> init_state=new LinkedHashMap<>();
    Set<String> dom_tf=new HashSet<>();
    Set<String> ALL_COLORS=new HashSet<>();
    ArrayList<Variable> ALL_TF=new ArrayList<>();
    Variable has_chassis;
    Variable has_roue_avg;
    Variable roue_avg;
    Variable has_roue_avd;
    Variable roue_avd;
    Variable has_roue_arg;
    Variable roue_arg;
    Variable has_roue_ard;
    Variable roue_ard;
    Variable has_corps;
    ArrayList<Variable> ALL_WHEELS=new ArrayList<>();
    ArrayList<Variable> ALL_SIDE=new ArrayList();
    ArrayList<Variable> ALL=new ArrayList<>();
    State init;
    public AssemblyLine() {
        dom_tf.add("true");
        dom_tf.add("false");
        ALL_COLORS.add("GRAY");
        ALL_COLORS.add("BLACK");
        ALL_COLORS.add("WHITE");
        ALL_COLORS.add("RED");
        ALL_COLORS.add("GREEN");
        ALL_COLORS.add("BLUE");
        ALL_COLORS.add("ORANGE");
        ALL_COLORS.add("YELLOW");

        ALL_TF.add(has_chassis=new Variable("chassis", dom_tf));
        ALL_TF.add(has_roue_avg=new Variable("has_roue_avg", dom_tf));
        ALL_TF.add(has_roue_avd=new Variable("has_roue_avd", dom_tf));
        ALL_TF.add(has_roue_arg=new Variable("has_roue_arg", dom_tf));
        ALL_TF.add(has_roue_ard=new Variable("has_roue_ard", dom_tf));
        ALL_TF.add(has_corps=new Variable("has_corps", dom_tf));

        ALL_WHEELS.add(roue_avg=new Variable("roue_avg",ALL_COLORS));
        ALL_WHEELS.add(roue_avd=new Variable("roue_avd",ALL_COLORS));
        ALL_WHEELS.add(roue_arg=new Variable("roue_arg",ALL_COLORS));
        ALL_WHEELS.add(roue_ard=new Variable("roue_ard",ALL_COLORS));

        ALL_SIDE.add(new Variable("avant",ALL_COLORS));
        ALL_SIDE.add(new Variable("gauche",ALL_COLORS));
        ALL_SIDE.add(new Variable("droite",ALL_COLORS));
        ALL_SIDE.add(new Variable("arriere",ALL_COLORS));
        ALL_SIDE.add(new Variable("toit",ALL_COLORS));
        ALL.addAll(ALL_SIDE);
        ALL.addAll(ALL_WHEELS);
        for (Variable var:ALL){
            init_state.put(var,"G");
        }
        for (Variable var:ALL_TF){
            init_state.put(var,"true");
        }
        init=new State(init_state);
    }

    public State simulation(){
        //on initialise toutes nos variables (et les possibilités de couleurs) (ici par ce que je sais pas ou les mettre autrement)
        //on fait un état initial, contenant chaque variable, avec la couleur grise
        LinkedHashMap<Variable,String> goal_state=new LinkedHashMap<>();
        //on crée une action permettant d ajouter le chassis(aucune precondition) avant de l appliquer a notre etat_but (le chassis permettant la pose de toutes autres pieces)
        LinkedHashMap<Variable,String> precond_etat= new LinkedHashMap<>();
        State precond=new State(precond_etat);
        LinkedHashMap<Variable,String> effect_etat= new LinkedHashMap<>();
        effect_etat.put(has_chassis,"true");
        State effect=new State(effect_etat);
        ArrayList<Rule> r_al=new ArrayList<>();
        r_al.add(new Rule(precond,effect));
        Action ajout_chassis =new Action(r_al);
        init=ajout_chassis.apply(init);
        goal_state.put(has_chassis,"true");
        State goal=new State(goal_state);

        //on vide effect (precond n a pas besoin) pour le re utiliser
        //on parcourt notre init_state pour créer chacune de ses valeurs à notre etat_but (qu on va accompagner d une couleur random)
        for (HashMap.Entry<Variable,String> jsp : init_state.entrySet()) {
            LinkedHashMap<Variable,String> precond_etatX=new LinkedHashMap<>();
            LinkedHashMap<Variable,String>effect_etatX=new LinkedHashMap<>();
            precond_etatX.put(has_chassis,"true");
            int randomNum = ThreadLocalRandom.current().nextInt(0, jsp.getKey().getDomaine().size());
            Object[] l_allcolor =Arrays.copyOf(jsp.getKey().getDomaine().toArray(), (jsp.getKey().getDomaine().size()));
            //System.out.println(l_allcolor[randomNum]);
            //System.out.println(jsp.getKey().getNom());
            effect_etatX.put(jsp.getKey(),(String) l_allcolor[randomNum]);
            State precondX=new State(precond_etatX);
            State effectX=new State(effect_etatX);
            Rule rule_random=new Rule(precondX,effectX);
            ArrayList<Rule> al_rule_random=new ArrayList<>();
            al_rule_random.add(rule_random);
            Action actionX=new Action(al_rule_random);
            goal=actionX.apply(goal);
        }
        return goal;
    }
}