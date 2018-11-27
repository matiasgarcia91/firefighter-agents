
import jason.environment.grid.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


/** class that implements the View of Domestic Robot application */
public class BoardView extends GridWorldView {

    BoardModel bmodel;

    public BoardView(BoardModel model) {
        super(model, "Firefighters Board", 700);
        bmodel = model;
        // defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
        setVisible(true);
        repaint();
    }

    /** draw application objects */
    @Override
    public void draw(Graphics g, int x, int y, int object) {
        // Location lRobot = bmodel.getAgPos(0);
        // super.drawAgent(g, x, y, Color.lightGray, -1);
        /*
        switch (object) {
        case BoardModel.FRIDGE:
            if (lRobot.equals(bmodel.lFridge)) {
                super.drawAgent(g, x, y, Color.yellow, -1);
            }
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, "Fridge ("+bmodel.availableBeers+")");
            break;
        case BoardModel.OWNER:
            if (lRobot.equals(bmodel.lOwner)) {
                super.drawAgent(g, x, y, Color.yellow, -1);
            }
            String o = "Owner";
            if (bmodel.sipCount > 0) {
                o +=  " ("+bmodel.sipCount+")";
            }
            g.setColor(Color.black);
            drawString(g, x, y, defaultFont, o);
            break;
        } */
        repaint();
    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        Location fighter1 = bmodel.getAgPos(0);
        c = Color.orange;
        super.drawAgent(g, x, y, c, -1);
        g.setColor(Color.black);
        super.drawString(g, x, y, defaultFont, "Fire"+(id+1));
        /*
        if (!lRobot.equals(bmodel.lOwner) && !lRobot.equals(bmodel.lFridge)) {
            c = Color.yellow;
            if (bmodel.carryingBeer) c = Color.orange;
            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.black);
            super.drawString(g, x, y, defaultFont, "Robot");

        }*/
    }
}
