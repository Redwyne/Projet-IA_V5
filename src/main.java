
import Diagnostic.Diagnoser;
import ExtractionDeConnaissances.*;
import examples.AssemblyLine;
import planning.Action;
import planning.PlanningProblem;
import planning.State;
import Representations.*;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class main {
    public static void main(String[] args) {





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Creation des Variables de voiture de BASE
        // Creation de la variable de couleur du toit et de son domaine

        Set<String> TF=new HashSet<>();
        TF.add("true");
        TF.add("false");

        Set<String> d_1 = new LinkedHashSet<>();
        //d_1.add("R");
        d_1.add("V");
        d_1.add("G");
        d_1.add("N");
        d_1.add("B");
        //d_1.add("P");
        //d_1.add("F");
        //d_1.add("Y");
        //d_1.add("A");
        //d_1.add("U");
        //d_1.add("J");

        Variable ct = new Variable("couleur toit", d_1);
        Variable has_ct=new Variable("has toit",TF);

        // Creation de la variable de couleur de l'aile gauche et de son domaine


        Variable cg = new Variable("couleur gauche", d_1);
        Variable has_cg=new Variable("has gauche",TF);

        // Creation de la variable de couleur de l'aile droite et de son domaine


        Variable cd = new Variable("couleur droite", d_1);
        Variable has_cd=new Variable("has droite",TF);

        // Creation de la variable de couleur des roues  et de son domaine

        Variable cr = new Variable("couleur roues", d_1);
        Variable has_cr=new Variable("has roues",TF);
        // Creation de la variable de couleur du volant et de son domaine


        Variable cv = new Variable("couleur volant", d_1);
        Variable has_cv=new Variable("has volant",TF);

        // Creation de la variable de couleur du capot et de son domaine
        Variable cc = new Variable("couleur capot", d_1);
        Variable has_cc=new Variable("has capot",TF);


        // Creation de la variable de couleur du hayon et de son domaine
        Variable ch = new Variable("Couleur hayon", d_1);
        Variable has_ch=new Variable("has hayon",TF);


/////////////////////////////////Creation des differentes Conditions////////////////////////////////////////////:




        // instanciation d'une regle de type Rule avec ses premisses (pr ) et ses conclusions ( ccl)

        HashMap<Variable, String> pr_etat = new HashMap<>();
        pr_etat.put(cd, "V");
        pr_etat.put(cg, "V");
        HashMap<Variable, String> ccl_etat = new HashMap<>();
        ccl_etat.put(cv, "B");
        Rule R1 = new Rule(new State(pr_etat),new State(ccl_etat));

        // instanciation d'une regle de type AllEqualConstraint


        Set<Variable> AEC = new HashSet<>();
        AEC.add(cd);
        AEC.add(cg);

        AllEqualConstraint A1 = new AllEqualConstraint(AEC);

        // instanciation d'une regle de type disjunction

        LinkedHashMap<Variable,String> dj = new LinkedHashMap<>();
        dj.put(ct,"V");
        dj.put(cc,"V");
        Disjunction D1 = new Disjunction(dj);

        // instanciation d'une regle de type IncompatibilityConstraint

        Set<Variable> ic = new HashSet<>();
        ic.add(ct);
        ic.add(cv);
        IncompatibilityConstraint IC1 = new IncompatibilityConstraint(ic);





/////////////////////////////////POUR LA PARTIE PPC //////////////////////////////////////////////////////////////////:






        //instanciation d'un tableau de toutes les contraintes

        ArrayList<Constraint> tableauc = new ArrayList<>();
        //tableauc.add(R1);
        tableauc.add(A1);
        //tableauc.add(D1);

        //instancation d'un tableau de toutes les variables et leurs domaines.

        ArrayList<Variable> tableauv = new ArrayList<>();
        tableauv.add(ct);
        tableauv.add(cg);
        tableauv.add(cd);
        tableauv.add(cr);
        tableauv.add(cv);






/////////////////////////////////// Pour la PARTIE Planning problem ////////////////////////////////////////////::

        /*Instanciation d'un systeme Premisses effet pour les actions
        //Premiere action -> COULEUR GAUCHE -> NOIR
        HashMap<Variable, String> premisses_etat = new HashMap<>();
        //premisses_etat1.put(cd, "V");
        //premisses_etat1.put(cd, "N");
        //premisses_etat1.put(cd, "B");
        //premisses_etat1.put(cd, "R");
        premisses_etat.put(cd, "G");
        */
        //pour changer la couleur de gauche, on doit avoir cg présent(=true)
        HashMap<Variable,String> precond_hm=new HashMap<>();
        precond_hm.put(has_cg,"true");
        State precond=new State(precond_hm);
        HashMap<Variable, String> effect_hm = new HashMap<>();
        effect_hm.put(cg, "N");
        State effect = new State(effect_hm);
        ArrayList<Rule> actioncgnoir=new ArrayList<>();
        Rule rulecgcolor=new Rule(precond,effect);
        actioncgnoir.add(rulecgcolor);
        Action cgnoir = new Action(actioncgnoir);

        HashMap<Variable,String> precond_hm1=new HashMap<>();
        precond_hm1.put(has_cd,"true");
        State precond1=new State(precond_hm1);
        HashMap<Variable, String> effect_hm1 = new HashMap<>();
        effect_hm1.put(cd, "B");
        State effect1 = new State(effect_hm1);
        ArrayList<Rule> actioncdblanc=new ArrayList<>();
        Rule rulecdcolor=new Rule(precond1,effect1);
        actioncdblanc.add(rulecdcolor);
        Action cdblanc = new Action(actioncdblanc);
/*
        //DEUXIEME ACTION -> COULEUR DROITE -> BLANC

        HashMap<Variable, String> premisses_etat1 = new HashMap<>();
        //premisses_etat1.put(cd, "V");
        //premisses_etat1.put(cd, "N");
        //premisses_etat1.put(cd, "B");
        //premisses_etat1.put(cd, "R");
        premisses_etat1.put(cd, "G");
        State premisses1 = new State(premisses_etat1);

        HashMap<Variable, String> effect_etat1 = new HashMap<>();
        effect_etat1.put(cd, "B");
        State effect1 = new State(effect_etat1);
        HashMap<Variable,String> hascdtrue=new HashMap<>(); hascdtrue.put(has_cd,"true");
        State s_cd=new State(hascdtrue);
        ArrayList<Rule> al_needhascd=new ArrayList<>();
        Rule needhascd=new Rule(s_cd,new State(new HashMap<>()));
        al_needhascd.add(needhascd);
        Action cdblanc = new Action(premisses1, effect1,al_needhascd);
*/

        HashMap<Variable,String> precond_hm2=new HashMap<>();
        precond_hm2.put(has_ct,"true");
        State precond2=new State(precond_hm2);
        HashMap<Variable, String> effect_hm2 = new HashMap<>();
        effect_hm2.put(ct, "R");
        State effect2 = new State(effect_hm2);
        ArrayList<Rule> actionctrouge=new ArrayList<>();
        Rule rulectcolor=new Rule(precond2,effect2);
        actionctrouge.add(rulectcolor);
        Action ctrouge = new Action(actionctrouge);

/*
        //TROISIEME ACTION -> COULEURTOIT -> ROUGE
        HashMap<Variable, String> premisses_etat2 = new HashMap<>();
        //premisses_etat1.put(ct, "V");
        //premisses_etat1.put(ct, "N");
        //premisses_etat1.put(ct, "B");
        //premisses_etat1.put(ct, "R");
        premisses_etat2.put(ct, "G");
        State premisses2 = new State(premisses_etat2);

        HashMap<Variable, String> effect_etat2 = new HashMap<>();
        effect_etat2.put(ct, "R");
        State effect2 = new State(effect_etat2);
        HashMap<Variable,String> hascttrue=new HashMap<>(); hascttrue.put(has_ct,"true");
        State s_ct=new State(hascttrue);
        ArrayList<Rule> al_needhasct=new ArrayList<>();
        Rule needhasct=new Rule(s_ct,new State(new HashMap<>()));
        al_needhasct.add(needhasct);
        Action ctrouge = new Action(premisses2, effect2,al_needhasct);
*/
        HashMap<Variable,String> precond_hm3=new HashMap<>();
        precond_hm3.put(has_cv,"true");
        State precond3=new State(precond_hm3);
        HashMap<Variable, String> effect_hm3 = new HashMap<>();
        effect_hm3.put(cv, "R");
        State effect3 = new State(effect_hm3);
        ArrayList<Rule> actioncvrouge=new ArrayList<>();
        Rule rulecvcolor=new Rule(precond3,effect3);
        actioncvrouge.add(rulecvcolor);
        Action cvrouge = new Action(actioncvrouge);


/*
        //Quatrieme ACTION -> peu importe COULEUR volant -> on la change en Rouge
        HashMap<Variable, String> premisses_etat3 = new HashMap<>();
        //premisses_etat1.put(cv, "V");
        //premisses_etat1.put(cv, "N");
        //premisses_etat1.put(cv, "B");
        //premisses_etat1.put(cv, "R");
        premisses_etat3.put(cv, "G");
        State premisses3 = new State(premisses_etat3);

        HashMap<Variable, String> effect_etat3 = new HashMap<>();
        effect_etat3.put(cv, "R");
        State effect3 = new State(effect_etat3);
        HashMap<Variable,String> hascvtrue=new HashMap<>(); hascvtrue.put(has_cv,"true");
        State s_cv=new State(hascvtrue);
        ArrayList<Rule> al_needhascv=new ArrayList<>();
        Rule needhascv=new Rule(s_cv,new State(new HashMap<>()));
        al_needhascv.add(needhascv);
        Action cvrouge = new Action(premisses3, effect3,al_needhascv);
*/

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///TEST DFS

        HashMap<Variable,String> initial = new HashMap<>();
        initial.put(cg,"G");
        initial.put(has_cg,"true");
        initial.put(cd,"G");
        initial.put(has_cd,"true");
        initial.put(ct,"G");
        initial.put(has_ct,"true");
        initial.put(cv,"G");
        initial.put(has_cv,"true");
        State init = new State(initial);




        HashMap<Variable,String> finale = new HashMap<>();
        finale.put(cg,"N");
        finale.put(has_cg,"true");
        finale.put(cd,"B");
        finale.put(has_cd,"true");
        finale.put(ct,"R");
        finale.put(has_ct,"true");
        finale.put(cv,"R");
        finale.put(has_cv,"true");

        State fin = new State( finale);
        ArrayList<Action> ouverture=new ArrayList<>();
        ouverture.add(cgnoir);
        ouverture.add(cdblanc);
        ouverture.add(ctrouge);
        ouverture.add(cvrouge);
        Stack<Action> plan=new Stack<>();
        ArrayList<HashMap<Variable,String>> closed=new ArrayList<>();

        PlanningProblem P1=new PlanningProblem(init,ouverture,fin);
        State etats = PlanningProblem.getInit_state();

        Stack<Action> enchain_action= PlanningProblem.DFS(P1,etats,plan,closed);
        System.out.println(enchain_action);
//////////////PARTIE EXCTRACTION DE CONNAISANCES /////////////////////////////////////////////:
        //crée une voiture(moche mais) cohérente, ce qui implique que chq element soit présent
        AssemblyLine sim=new AssemblyLine();
        State lel=sim.simulation();
        String fks="";
        for(Map.Entry<Variable,String> elt:lel.getEtat().entrySet()){
            fks+=elt.getKey().getNom()+": "+elt.getValue()+" // ";
        }
        System.out.println(fks);



        Set<String> set = new HashSet<>();
        set.add("0");
        set.add("1");
        Variable A = new Variable("A", set);
        Variable B = new Variable("B", set);
        Variable C = new Variable("C", set);
        Variable D = new Variable("D", set);
        Variable E = new Variable("E", set);
        HashMap<Variable, String> tran0 = new HashMap<>();
        tran0.put(A, "1");
        tran0.put(B, "1");
        tran0.put(C, "1");
        tran0.put(D, "1");
        tran0.put(E, "1");
        Transactions transac0 = new Transactions(tran0);

        HashMap<Variable, String> tran1 = new HashMap<>();
        tran1.put(A, "1");
        tran1.put(B, "0");
        tran1.put(C, "1");
        tran1.put(D, "0");
        tran1.put(E, "0");
        Transactions transac1 = new Transactions(tran1);

        HashMap<Variable, String> tran2 = new HashMap<>();
        tran2.put(A, "1");
        tran2.put(B, "1");
        tran2.put(C, "1");
        tran2.put(D, "1");
        tran2.put(E, "0");
        Transactions transac2 = new Transactions(tran2);

        HashMap<Variable, String> tran3 = new HashMap<>();
        tran3.put(A, "0");
        tran3.put(B, "1");
        tran3.put(C, "1");
        tran3.put(D, "0");
        tran3.put(E, "0");
        Transactions transac3 = new Transactions(tran3);

        HashMap<Variable, String> tran4 = new HashMap<>();
        tran4.put(A, "1");
        tran4.put(B, "1");
        tran4.put(C, "1");
        tran4.put(D, "0");
        tran4.put(E, "0");
        Transactions transac4 = new Transactions(tran4);

        HashMap<Variable, String> tran5 = new HashMap<>();
        tran5.put(A, "0");
        tran5.put(B, "0");
        tran5.put(C, "0");
        tran5.put(D, "0");
        tran5.put(E, "1");
        Transactions transac5 = new Transactions(tran5);

        ArrayList<Variable> ListeV = new ArrayList<>();
        ListeV.add(A);
        ListeV.add(B);
        ListeV.add(C);
        ListeV.add(D);
        ListeV.add(E);

        ArrayList<Transactions> ListeT = new ArrayList<>();
        ListeT.add(transac0);
        ListeT.add(transac1);
        ListeT.add(transac2);
        ListeT.add(transac3);
        ListeT.add(transac4);
        ListeT.add(transac5);

        Set<String> domaine_v=new HashSet<>();
        domaine_v.add("noir");
        domaine_v.add("rouge");
        domaine_v.add("blanc");
        Set<Variable> setvar = new HashSet<>();
        setvar.add(new Variable("couleur_gauche",domaine_v));
        setvar.add(new Variable("couleur_droite",domaine_v));
        setvar.add(new Variable("couleur_toit",domaine_v));
        setvar.add(new Variable("toit_ouvrant",TF));
        setvar.add(new Variable("sono",TF));
        setvar.add(new Variable("couleur_hayon",domaine_v));
        setvar.add(new Variable("couleur_capot",domaine_v));

        DBReader dbr=new DBReader(setvar);
        try {
            String pathDb= new File("src/db.txt").getAbsolutePath();

            Database db=dbr.importDB(pathDb);

            /*ne pas lancer, c est un peu long*/
            FrequentItemsetMinerBoolean fim=new FrequentItemsetMinerBoolean(db);
            HashMap<Set<Map.Entry<Variable,String>>,Integer> jspjetestedelamerde= fim.FrequentItemsets(100);
            for (Map.Entry<Set<Map.Entry<Variable,String>>,Integer> elt:jspjetestedelamerde.entrySet()){
                Iterator<Map.Entry<Variable,String>> oui=elt.getKey().iterator();
                String fkkks="CECI EST UN SET DE VARIABLE: ";
                while(oui.hasNext()){
                    Map.Entry<Variable,String>obj=oui.next();
                    fkkks+=obj.getKey().getNom();
                }
                System.out.println(fkkks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*BooleanDatabase madatabase = new BooleanDatabase(ListeT, ListeV);
        FrequentItemsetMinerBoolean fim = new FrequentItemsetMinerBoolean(madatabase);
        System.out.println(fim.FrequentItemsets(2));
        */


//////////////////////////////////////////PArtie DIAGNOSER//////////////////////////////////////////////////////////////






        HashMap <Variable,String> ens_choix = new HashMap<>();
        ens_choix.put(cg,"R");
        ens_choix.put(cd,"R");
        ens_choix.put(ct,"R");
        ens_choix.put(cr,"R");
        ens_choix.put(cv,"R");

        ArrayList<Constraint> N_constr= new ArrayList<>();


        Diagnoser diagnostic = new Diagnoser (ens_choix,N_constr);


        Set<Variable> set_aec=new HashSet<>();
        set_aec.add(ct);
        set_aec.add(cc);
        set_aec.add(ch);
        AllEqualConstraint aec=new AllEqualConstraint(set_aec);


        HashMap <Variable,String> test = new HashMap<>();
        test.put(ch,"R");
        test.put(cd,"N");
        test.put(cc,"N");
        test.put(ct,"R"); //si il y est pas faut que ca renvoie pas False
        Set<Variable> TCH = new HashSet<>();
        TCH.add(ct);
        TCH.add(cc);
        TCH.add(ch);
        AllEqualConstraint test_aec= new AllEqualConstraint(TCH);









        ///////////////////::QUElQUES TESTS SUPLLEMENTAIRES /////////////////////////////////////




/*
        //backtracking sans solution
        //backtracking_nosol.Test(tableauv,tableauc);

        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        //backtracking avec solution
        backtracking letestnumero678=new backtracking(tableauv,tableauc);
        letestnumero678.solution();
*/







    }

}