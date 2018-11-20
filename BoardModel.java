import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of the application */
public class BoardModel extends GridWorldModel {

  public static final int GSize = 7;
  public static final int Firefighters = 3;
  public static final int Plane = 1;

  public BoardModel() {
    super(GSize, GSize, Firefighters + Plane);
    // Set agents position in a random nature
    // setAgPos(0, GSize/2, GSize/2);
  }
}
