package planning;

import java.util.*;

import static java.util.Collections.reverse;
import planning.*;
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
            //comment recuperer le argmin -> argmin est le  state dans open qui a la plus petit distance -> voir option en desosus
            State et=Argmin(open,distance);
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

    private Stack<ActionWithCost>getdijkstraplan(HashMap<State,State> father, HashMap<State,ActionWithCost> actions, ArrayList<State> goals,HashMap<State,Integer> distance){
        Stack<ActionWithCost> plan=new Stack<>();
        State goal=Argmin(goals,distance);
        while (!goals.isEmpty()){
            plan.push(actions.get(goal));
            goal=father.get(goal);
        }

        reverse(plan);
        return plan;
    }

    public Stack<ActionWithCost> Astar(PlanningProblem problem){
        ArrayList<State> open=new ArrayList<>();
        HashMap<State,Integer> distance=new HashMap<>();
        HashMap<State,State> father=new HashMap<>();
        distance.put(problem.init_state,0);

        return PlanningProblem.get_bfs_plan();
    }

    private State Argmin(ArrayList<State> Aparcourir,HashMap<State,Integer> Averifier){ //La je fait une fonction qui parcourt array et rend le state qui a le plus petit integer
        int cpt=0;
        State res=null;
        for (State i:Aparcourir){
            if (Averifier.get(i)>cpt){
                cpt=Averifier.get(i);
                res=i;
            }

        }
        return res;
    }
}
