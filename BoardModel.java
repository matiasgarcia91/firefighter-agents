import java.util.*;
import java.lang.*;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of the application */
public class BoardModel extends GridWorldModel {

  public static final int m = 7;
  public static final int n = 7;
  public static final int GSize = 7;
  public static final int Firefighters = 3;
  public static final int Plane = 1;

  public static final int WFIRE  = 16; // garbage code in grid model
  public static final int SFIRE  = 32; // garbage code in grid model

  int[][] victims = new int[n][m];
  int[][] fires = new int[n][m];
  Map<String, Integer> agentsId = new HashMap<String, Integer>();

  Random rand = new Random();

  public BoardModel() {
    super(GSize, GSize, Firefighters + Plane);
    // Set agents position in a random nature
    boolean[][] used = new boolean[n][m];
    for (int i = 0; i < Firefighters + Plane; i++) {
    	int x, y;
    	do {
	    	x = rand.nextInt(n);
	    	y = rand.nextInt(m);
	    	setAgPos(i, x, y);
    	} while (used[x][y]);
    	used[x][y] = true;
    }
    for (int i = 0; i < Firefighters; i++) {
    	agentsId.put("firefighter" + (i + 1), i);
    }

    // initialize fires and victims
    for (int i = 0; i < n; i++) {
    	for (int j = 0; j < m; j++) {
      		int p = rand.nextInt(5);

      		if (p >= 3) { // there is a fire
	      		if (p == 3) {
	      			fires[i][j] = 1; // weak fire
	      			add(WFIRE, i, j);
	      		}
	      		if (p == 4) {
	      			fires[i][j] = 2; // strong fire
	      			add(SFIRE, i, j);
	      		}

	      		// set random number of victims
	      		victims[i][j] = rand.nextInt(5);
      		}
    	}
    }
  }

  int getNbOfFFighters() {
    return Firefighters;
  }

  boolean fireAt(Location agt) {
    return fires[agt.x][agt.y] > 0;
  }

  boolean wFireAt(Location agt) {
    return fires[agt.x][agt.y] == 1;
  }

  boolean victimsAt(Location agt) {
    return victims[agt.x][agt.y] > 0;
  }

  boolean squashWeakFire(String agtName) {
    int id = agentsId.get(agtName);
    Location l = getAgPos(id);
    int isFire = fires[l.x][l.y];
    if(isFire == 1) {
      fires[l.x][l.y] = 0;
      remove(WFIRE, l.x, l.y);
      return true;
    } else {
      return false;
    }
  }


  boolean tryToAssist(String agtName, int x, int y) {
    int id = agentsId.get(agtName);
    Location f1 = getAgPos(id);
     if (Math.abs(x - f1.x) <= 1 && Math.abs(y - f1.y) <= 1) {
    	setAgPos(id, x, y);
      System.out.println(" ====== assiting:"+agtName+" ====== ");
    }
    else
    	setAgPos(id, f1);
      System.out.println(" ====== cant assist:"+agtName+" ====== ");
     return true;
   }

  boolean squashStrongFire() {
    Location l = getAgPos(Firefighters);
    int isFire = fires[l.x][l.y];
    if(isFire == 2) {
      fires[l.x][l.y] = 0;
      remove(SFIRE, l.x, l.y);
      return true;
    } else {
      return false;
    }
  }

  boolean grabVictim(String agtName) {
    int id = agentsId.get(agtName);
    Location l = getAgPos(id);
    int victim = victims[l.x][l.y];
    if(victim > 0) {
      victims[l.x][l.y]--;
      return true;
    } else {
      return false;
    }
  }

  boolean dropVictim(String agtName) {
    int id = agentsId.get(agtName);
    Location l = getAgPos(id);
    victims[l.x][l.y]++;
    return true;
  }

  boolean walk(String agtName) {
      int id = agentsId.get(agtName);
      Location f1 = getAgPos(id);
      int dx = rand.nextInt(3) - 1;
      int dy = rand.nextInt(3) - 1;
      f1.x = Math.max(f1.x + dx, 0);
      f1.y = Math.max(f1.y + dy, 0);
      f1.x = Math.min(f1.x, getWidth() - 1);
      f1.y = Math.min(f1.y, getHeight() - 1);

      boolean canMove = true;
      for (int i = 0; i < Firefighters; i++) {
        Location ltemp = getAgPos(i);
        if (ltemp.equals(f1)) {
          canMove = false;
        }
      }
      if (canMove) {
        setAgPos(id, f1);
      } else {
        setAgPos(id, getAgPos(id));
      }

      // repaint the fridge and owner locations
      if (view != null) {
          //view.update(lFridge.x,lFridge.y);
          //view.update(lOwner.x,lOwner.y);
      }
      return true;
  }

  boolean goAway(String agtName) {
      int id = agentsId.get(agtName);
      Location f1 = getAgPos(id);
      int dx = rand.nextInt(3) - 1;
      int dy = rand.nextInt(3) - 1;
      f1.x = Math.max(f1.x + dx, 0);
      f1.y = Math.max(f1.y + dy, 0);
      f1.x = Math.min(f1.x, getWidth() - 1);
      f1.y = Math.min(f1.y, getHeight() - 1);

      boolean canMove = true;
      for (int i = 0; i < Firefighters; i++) {
        Location ltemp = getAgPos(i);
        if (ltemp.equals(f1)) {
          canMove = false;
        }
      }
      if (canMove) {
        setAgPos(id, f1);
      } else {
        setAgPos(id, getAgPos(id));
      }

      // repaint other agents;
      if (view != null) {
      /*  for (int i = 0; i< (Firefighters + Plane); i++) {
          Location l = getAgPos(i);
          view.update(l.x, l.y);
        }*/
          //view.update(lFridge.x,lFridge.y);
          //view.update(lOwner.x,lOwner.y);
      }
      return true;
  }

  boolean planeGoTo(int x, int y) {
    Location destiny = new Location(x, y);
    setAgPos(Firefighters, destiny);
    return squashStrongFire();
  }


}
