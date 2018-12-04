// Environment code for project Firefighters.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import jason.environment.grid.Location;
import java.util.logging.*;

public class FireEnv extends Environment {

    // Actions
    public static final Literal owf = Literal.parseLiteral("squash(weakFire)");
    public static final Literal osf = Literal.parseLiteral("squash(strongFire)");
    public static final Literal cf = Literal.parseLiteral("call(firefighters)");
    public static final Literal wk = Literal.parseLiteral("walk(FF)");
    public static final Literal pg = Literal.parseLiteral("planeGoTo(X, Y)");


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
            String agentName = "firefighter" + (i + 1);
            clearPercepts(agentName);
            // get the agent location
            Location lAgent = model.getAgPos(i);
            addPercept(agentName, Literal.parseLiteral("location("+lAgent+")"));

            // add percept if agent is at fire
            if (model.fireAt(lAgent)) {
                addPercept(agentName, Literal.parseLiteral("fireAt("+agentName+")"));
                if (model.victimsAt(lAgent)) {
                    addPercept(agentName, Literal.parseLiteral("victimsAt("+agentName+")"));
                }
                // add percept according to type of fire
                if (model.wFireAt(lAgent))
                    addPercept(agentName, Literal.parseLiteral("weakFireAt("+agentName+")"));
                else
                    addPercept(agentName, Literal.parseLiteral("strongFireAt("+agentName+")"));
            } else {
              addPercept(agentName, Literal.parseLiteral("noFireAt("+agentName+")"));
            }

            if(model.firefighterHasVictim(i)) {
              addPercept(agentName, Literal.parseLiteral("hasVictim("+agentName+")"));
            }
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
      System.out.println("["+agName+"] doing: "+action);
      boolean result = false;

      if (action.getFunctor().equals("squashWeakFire")) {
      	result = model.squashWeakFire(agName);
      }
      if (action.getFunctor().equals("squashStrongFire")) {
      	result = model.squashStrongFire();
      }
      if (action.getFunctor().equals("grabVictim")) {
      	result = model.grabVictim(agName);
      }
      if (action.getFunctor().equals("dropVictim")) {
      	result = model.dropVictim(agName);
      }
      if (action.getFunctor().equals("walk")) {
      	result = model.walk(agName);
      }

      if (action.getFunctor().equals("planeGoTo")) {
        int x = Integer.parseInt(action.getTerm(0).toString());
        int y = Integer.parseInt(action.getTerm(1).toString());
        result = model.planeGoTo(x, y);
      }

      if (action.getFunctor().equals("tryToAssist")) {
      	String agentName = action.getTerm(0).toString();
        int x = Integer.parseInt(action.getTerm(1).toString());
        int y = Integer.parseInt(action.getTerm(2).toString());
        result = model.tryToAssist(agentName, x, y);
      }

      if (result) {
            updatePercepts();
            try {
                Thread.sleep(100);
            } catch (Exception e) {}
        }
        return result;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
