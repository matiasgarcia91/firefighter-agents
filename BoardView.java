
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
        switch (object) {
	        case BoardModel.WFIRE:
	            drawFire(g, x, y, true);
	            break;
	        case BoardModel.SFIRE:
	            drawFire(g, x, y, false);
	            break;
        }

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
        String name;
        if (id == -1) return;
        if (id == 3) {
          c = Color.blue;
          name = "Plane";
        } else {
          c = Color.orange;
          name = "Fighter" + (id + 1);
        }
        super.drawAgent(g, x, y, c, -1);
        g.setColor(Color.black);
        super.drawString(g, x, y, defaultFont, name);

    }

    public void drawFire(Graphics g, int x, int y, boolean isWeak) {
        super.drawObstacle(g, x, y);
        g.setColor(Color.white);
        if (isWeak)
        	drawString(g, x, y, defaultFont, "weak"+ " " + bmodel.victims[x][y]);
        else
        	drawString(g, x, y, defaultFont, "strong"+ " " +bmodel.victims[x][y]);
    }
}
