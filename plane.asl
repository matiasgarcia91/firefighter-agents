// Agent plane in project Firefighters.mas2j

/* Initial beliefs and rules */

fullTank(plane).

/* Initial goals */

/* Plans */

+!goToFire(X, Y): fullTank(plane) <- planeGoTo(X, Y); -fullTank(plane); !refill(plane).
+!goToFire(X, Y).

+!refill(plane) <- +fullTank(plane).
