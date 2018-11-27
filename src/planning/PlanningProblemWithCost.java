
package planning;

import java.util.*;

import static java.util.Collections.reverse;

public class PlanningProblemWithCost extends PlanningProblem{

    public PlanningProblemWithCost(State init_state, ArrayList<Action> ouverts, State final_states) {
        super(init_state, ouverts, final_states);
    }

    public Stack<ActionWithCost> Dijkstra(){
        HashMap<State,Integer> distance=new HashMap<>();
        HashMap<State,State> father=new HashMap<>();
        HashMap<State,ActionWithCost> plan=new HashMap<>();
        ArrayList<State> goals=new ArrayList<>();
        ArrayList<State> open=new ArrayList<>();
        open.add(PlanningProblem.getInit_state());
        distance.put(PlanningProblem.getInit_state(),0);
        father.put(PlanningProblem.getInit_state(),null);

        while (!open.isEmpty()){
            //comment recuperer le argmin
            State et=.ArgMin();
            open.remove(et);
            if(ActionWithCost.satisfies(et,this.getFinal_states())){
                goals.add(et);
            }
            for (Action act:this.getOuverts()){
                if (ActionWithCost.is_applicable(act,et)){
                    State next=act.apply(et);
                    if (!distance.containsKey(next)){
                        distance.put(next, Integer.MAX_VALUE);
                    }
                    if ((distance.get(next)>(distance.get(et)+ActionWithCost.cout))) {
                        distance.put(next,distance.get(et)+ActionWithCost.cout);
                        father.put(next, et);
                        plan.put(next, (ActionWithCost) act);
                        open.add(next);
                    }
                }
            }
        }
        return getdijkstraplan(father,plan,goals,distance);
    }

    public Stack<ActionWithCost>getdijkstraplan(HashMap<State,State> father, HashMap<State,ActionWithCost> actions, ArrayList<State> goals,HashMap<State,Integer> distance){
        Stack<ActionWithCost> plan=new Stack<>();
        State goal=argmin(goals);
        while (!goals.isEmpty()){
            plan.push(actions.get(goal));
            goal=father.get(goal);
        }

        reverse(plan);
        return plan;
    }
}
