// Environment code for project Firefighters.mas2j

import jason.asSyntax.*;
import jason.environment.*;
import jason.environment.grid.Location;
import java.util.logging.*;

public class FireEnv extends Environment {

    // common literals
    public static final Literal fa  = Literal.parseLiteral("open(fridge)");

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
            if (victims > 0) {
                addPercept(agentName, Literal.parseLiteral("victims("+lAgent+","+victims+")"));
            }

            // add beer "status" the percepts
            if (model.fridgeOpen) {
                addPercept("robot", );
            }
        }

        

        
        if (model.sipCount > 0) {
            addPercept("robot", hob);
            addPercept("owner", hob);
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: "+action+", but not implemented!");
        if (true) { // you may improve this condition
             informAgsEnvironmentChanged();
        }
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}

