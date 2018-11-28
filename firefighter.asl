// Agent firefighter in project Firefighters.mas2j

/* Initial beliefs and rules */

/* Initial goals */

!tryToWalk(FF).

/*!start.*/

/* Plans */

/*+!start : true <- .print("hello world.").*/

+fireAt(X) : true <- .print("fire").
+!tryToWalk(FF) : noFireAt(FF) <- .print("noFire"); walk(FF); !tryToWalk(FF).
