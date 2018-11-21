// Environment code for project Firefighters.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import jason.environment.grid.Location;
import java.util.logging.*;

public class FireEnv extends Environment {

    // Belifs
    public static final Literal fa  = Literal.parseLiteral("fire()");
    public static final Literal wf  = Literal.parseLiteral("weakFire()");
    public static final Literal sf  = Literal.parseLiteral("strongFire()");

    // Actions
    public static final Literal owf = Literal.parseLiteral("squash(weakFire)");
    public static final Literal osf = Literal.parseLiteral("squash(strongFire)");
    public static final Literal gv = Literal.parseLiteral("grab(victim)");
    public static final Literal dv = Literal.parseLiteral("drop(victim)");
    public static final Literal cf = Literal.parseLiteral("call(firefighters)");
    public static final Literal cp = Literal.parseLiteral("call(plane)");
    public static final Literal bd = Literal.parseLiteral("broadcast(danger)");
    public static final Literal mv = Literal.parseLiteral("move(Y)");


    private Logger logger = Logger.getLogger("Firefighters.mas2j."+FireEnv.class.getName());

    BoardModel model;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        model = new BoardModel();

        if (args.length == 1 && args[0].equals("gui")) {
            BoardView view  = new BoardView(model);
            model.setView(view);
        }

        updatePercepts();
    }

    /** creates the agents percepts based on the BoardModel */
    void updatePercepts() {
        clearPercepts("plane");

        for (int i = 0; i < model.getNbOfFFighters(); i++) {
            string agentName = "firefighter" + i + 1;
            clearPercepts(agentName);

            // get the agent location
            Location lAgent = model.getAgPos(i);
            addPercept(agentName, Literal.parseLiteral("location("+lAgent+")"));
            // add percept if agent is at fire
            if (model.fireAt(lAgent)) {
                addPercept(agentName, fa);
                // add percept according to type of fire
                if (model.wFireAt(lAgent))
                    addPercept(agentName, wf);
                else
                    addPercept(agentName, sf);
            }
            // add number of victims in current cell to percepts if any
            int victims = model.victimsAt(lAgent);
            addPercept(agentName, Literal.parseLiteral("victims("+victims+")"));
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
      System.out.println("["+ag+"] doing: "+action);
      boolean result = false;
      if (action.equals())


    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
