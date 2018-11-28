package planning;

import Representations.Variable;

import java.util.*;

import static jdk.nashorn.internal.objects.NativeArray.*;


public class PlanningProblem {
    public static State init_state;
    public ArrayList<Action> ouverts;
    public State final_states;

    public PlanningProblem(State init_state, ArrayList<Action> ouverts, State final_states) {
        this.init_state = init_state;
        this.ouverts = ouverts;
        this.final_states = final_states;
    }
    public static State getInit_state() {
        return init_state;
    }

    public ArrayList<Action> getOuverts() {
        return ouverts;
    }

    public State getFinal_states() {
        return final_states;
    }

    public static Stack<Action> DFS(PlanningProblem problem, State etat, Stack<Action> plan, ArrayList<LinkedHashMap<Variable, String>> closed) {
        if (Action.satisfies(etat, problem.getFinal_states())) {
            return plan;
        } else {
            for (Action act : problem.getOuverts()) {
                //l etat avance mais s arrete on sait pas pq (et le plan ne recupere pas les actions a partir de la 2nde)
                //en fait on doit faire une copie de next(state ou hashmap?) pour l ajouter dans closed, sinon qd on le redefinit, il est changé dans closed
                State next = act.apply(etat);
                if (!closed.contains(next.getEtat())) {
                    //on agit sur un STACK(pile) avec push pour ajouter en haut, pop pour retirer en haut
                    plan.push(act);
                    closed.add((LinkedHashMap<Variable,String>)next.getEtat().clone());
                    Stack<Action> subplan = DFS(problem, next, plan, closed);
                    if (!(subplan == null)) {
                        return subplan;
                    } else {
                        plan.pop();
                    }
                }


        }
            return null;
        }
    }
    public Object BFS(PlanningProblem problem){
        HashMap<State,State> father=new HashMap<>();
        HashMap<State,Action> plan=new HashMap<>();
        ArrayList<State> closed=new ArrayList<>();
        Queue<State> open=new ArrayDeque<>();
        while (!(open.isEmpty())){
            //on agit sur une File avec offer pour ajouter en haut, poll pour retirer en haut
            State etat=open.poll();
            closed.add(etat);
            ArrayList<Action> aled=new ArrayList<>();
            for (Action act:problem.getOuverts()){
                if (Action.is_applicable(act,etat)){
                    aled.add(act);
                    State next = act.apply(etat);
                    if(!(closed.contains(next)) && !(open.contains(next))){
                        State next_c=new State((LinkedHashMap<Variable,String>)next.getEtat().clone());
                        father.put(next_c,etat);
                        plan.put(next_c,act);
                        if(Action.satisfies(next,problem.getFinal_states())){
                            return get_bfs_plan(father,plan,next);
                        }
                        else{
                            open.offer(next_c);
                        }
                    }
                }
            }
        }

        return null;
    }
    private Stack<Action> get_bfs_plan(HashMap<State,State> father,HashMap<State,Action> action,State goal){
        Stack<Action> plan=new Stack<>();
        while (goal!=null){

            plan.push(action.get(goal));
            goal=father.get(goal);
        }
        reverse(plan);
        return plan;
    }


}
//test pour github
//2eme test pour github