// Agent plane in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").

+!goToHelp(X, Y).

+!goToFire(X, Y) : true <- planeGoTo(X, Y); squashStrongFire(plane).
