import java.util.*;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of the application */
public class BoardModel extends GridWorldModel {

  public static final int m = 7;
  public static final int n = 7;
  public static final int GSize = 7;
  public static final int Firefighters = 3;
  public static final int Plane = 1;

  int[][] victims = new int[n][m];
  int[][] fires = new int[n][m];
  Map<String, Integer> agentsId = new HashMap<String, Integer>();

  Random rand = new Random();

  public BoardModel() {
    super(GSize, GSize, Firefighters + Plane);
    // Set agents position in a random nature
    for (int i = 0; i < Firefighters + Plane; i++) {
    	setAgPos(i, rand.nextInt(GSize), rand.nextInt(GSize));
    }
    for (int i = 0; i < Firefighters; i++) {
    	agentsId.put("firefighter" + i + 1, i);
    }

    // initialize fires and victims
    for (int i = 0; i < n; i++) {
    	for (int j = 0; j < m; j++) {
      		int p = rand.nextInt(5);

      		if (p >= 3) { // there is a fire
	      		if (p == 3) fires[i][j] = 1; // weak fire
	      		if (p == 4) fires[i][j] = 2; // strong fire

	      		// set random number of victims
	      		victims[i][j] = rand.nextInt(5);
      		}
    	}
    }
  }

  int getNbOfFFighters() {
    return Firefighters;
  }

  boolean squashWeakFire(String agtName) {
    int id = agentsId.get(agtName);
    Location l = getAgPos(id);
    int isFire = fires[l.x][l.y];
    if(isFire == 1) {
      fires[l.x][l.y] = 0;
      return true;
    } else {
      return false;
    }
  }

  boolean squashStrongFire(String agtName) {
    int id = agentsId.get(agtName);
    Location l = getAgPos(id);
    int isFire = fires[l.x][l.y];
    if(isFire == 2) {
      fires[l.x][l.y] = 0;
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

  boolean walk(String agtName, Location dest) {
      int id = agentsId.get(agtName);
      Location r1 = getAgPos(id);
      if (r1.x < dest.x)        r1.x++;
      else if (r1.x > dest.x)   r1.x--;
      if (r1.y < dest.y)        r1.y++;
      else if (r1.y > dest.y)   r1.y--;
      setAgPos(id, r1); // move the robot in the grid

      // repaint the fridge and owner locations
      if (view != null) {
          //view.update(lFridge.x,lFridge.y);
          //view.update(lOwner.x,lOwner.y);
      }
      return true;
  }






}
